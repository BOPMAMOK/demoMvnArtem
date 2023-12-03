package com.web.api.repositories.clothing;

import com.web.api.exception.NotFoundException;
import com.web.api.model.Clothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClothingRepositoryH2 implements ClothingRepository {

    private static final String CREATE = """
            INSERT INTO CLOTHINGS (CLOTHING_ID, SHELF_ID, ISSUE_DATE, CLOTHING_PRICE, IS_MALE, TYPE)
            VALUES (:clothingId, :shelfId, :issueDate, :clothingPrice, :male, :type.id)
            """;

    private static final String UPDATE = """
            UPDATE CLOTHINGS SET SHELF_ID = :shelfId,
            ISSUE_DATE = :issueDate,
            CLOTHING_PRICE = :clothingPrice,
            IS_MALE = :male,
            TYPE = :type.id
            WHERE CLOTHING_ID = :clothingId
            """;

    private final RowMapper<Clothing> rowMapper = new DataClassRowMapper<>(Clothing.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ClothingRepositoryH2(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Clothing> getClothings() {
        return jdbcTemplate.query("SELECT * FROM CLOTHINGS", rowMapper);
    }

    public Clothing getClothing(int clothingId) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM CLOTHINGS WHERE CLOTHING_ID = ?", rowMapper, clothingId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Clothing with id = [" + clothingId + "] not found", e);
        }
    }

    public void createClothing(Clothing clothing) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(clothing);
        namedParameterJdbcTemplate.update(CREATE, parameterSource);
    }

    public void updateClothing(Clothing clothing, int clothingId) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(clothing);
        namedParameterJdbcTemplate.update(UPDATE, parameterSource);
    }

    public void deleteClothing(int clothingId) {
        jdbcTemplate.update("DELETE FROM CLOTHINGS WHERE CLOTHING_ID = ?", clothingId);
    }
}