package prography.table_tennis.repository.jdbcRepository;

import prography.table_tennis.dto.common.UserBulkInsertDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserJdbcRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void bulkInsertUser(List<UserBulkInsertDto> dtos) {
        String sql = "INSERT INTO users (faker_id, name, email, status) " +
                "VALUES (:fakerId, :name, :email, :status)";

        SqlParameterSource[] sqlParameterSource =
                dtos.stream().map(
                                BeanPropertySqlParameterSource::new).
                        toArray(SqlParameterSource[]::new);

        jdbcTemplate.batchUpdate(sql, sqlParameterSource);
    }

}
