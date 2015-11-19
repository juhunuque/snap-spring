package org.training.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.training.controllers.exceptions.DepartmentNotFoundException;
import org.training.domain.Department;

import java.util.Collections;
import java.util.Map;

@Repository
@Qualifier("jdbc")
public class JdbcDepartmentRepository implements DepartmentRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Department findOne(Integer deptId) {
        logger.trace("Finding department by id {}", deptId);
        Map<String, Object> params = Collections.singletonMap("id", deptId);
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Department WHERE id=:id", params, (rs, rowNum) -> {
                Department dpt = new Department();
                dpt.setId(rs.getInt(1));
                dpt.setName(rs.getString(2));
                return dpt;
            });
        }
        catch (EmptyResultDataAccessException empty) {
            logger.error("Failed to find Department for id {}", deptId, empty);
            throw new DepartmentNotFoundException(deptId);
        }
    }

    @Override
    public Department save(Department department) {
        if (exists(department.getId())) {
            return update(department);
        }
        return insert(department);
    }

    private Department insert(Department department) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new MapSqlParameterSource("name", department.getName());
        jdbcTemplate.update("INSERT INTO Department(name) VALUES(:name)", params, keyHolder, new String[]{"id"});
        Integer deptId = keyHolder.getKey().intValue();
        department.setId(deptId);
        return department;
    }

    private Department update(Department department) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(department);
        jdbcTemplate.update("UPDATE Department SET name=:name WHERE id=:id", params);
        return department;
    }

    @Override
    public boolean exists(Integer deptId) {
        SqlParameterSource params = new MapSqlParameterSource("id", deptId);
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Department WHERE id=:id", params, Integer.class) > 0;
    }

    @Override
    public void delete(Integer deptId) {
        SqlParameterSource params = new MapSqlParameterSource("id", deptId);
        jdbcTemplate.update("DELETE FROM Department WHERE id=:id", params);
    }
}
