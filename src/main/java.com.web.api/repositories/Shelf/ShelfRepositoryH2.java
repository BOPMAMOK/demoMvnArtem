package com.web.api.repositories.shelf;

import com.web.api.exception.NotFoundException;
import com.web.api.model.Clothing;
import com.web.api.model.Shelf;
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
public class ShelfRepositoryH2 implements ShelfRepository {

    private static final String CREATE = """
            INSERT INTO SHELFS (SHELF_ID, SHELF_NAME, PRODUCT, FACTORY)
            VALUES (:shelfId, :shelfName, :product, :factory)
            """;
    private static final String UPDATE = """
            UPDATE SHELFS SET SHELF_NAME = :shelfName,
            PRODUCT = :product,
            FACTORY = :factory,
            WHERE SHELF_ID = :shelfId
            """;

    private final RowMapper<Shelf> rowMapper = new DataClassRowMapper<>(Shelf.class);
    private final RowMapper<Clothing> clothingRowMapper = new DataClassRowMapper<>(Clothing.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ShelfRepositoryH2(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Shelf> getShelfs() {
        return jdbcTemplate.query("SELECT * FROM SHELFS", rowMapper);
    }

    @Override
    public Shelf getShelf(int shelfId) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM SHELFS WHERE SHELF_ID = ?", rowMapper, shelfId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Shelf with id = [" + shelfId + "] not found", e);
        }
    }

    @Override
    public List<Clothing> getClothingsByShelfId(int shelfId) {
        return jdbcTemplate.query("SELECT * FROM CLOTHINGS WHERE SHELF_ID = ?", clothingRowMapper, shelfId);
    }

    @Override
    public void createShelf(Shelf shelf) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(shelf);
        namedParameterJdbcTemplate.update(CREATE, parameterSource);
    }

    @Override
    public void updateShelf(Shelf shelf, int shelfId) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(shelf);
        namedParameterJdbcTemplate.update(UPDATE, parameterSource);
    }

    @Override
    public void deleteShelf(int shelfId) {
        jdbcTemplate.update("DELETE FROM SHELFS WHERE SHELF_ID =?", shelfId);
    }
}