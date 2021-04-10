package tech.itpark.web.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tech.itpark.web.dto.SaleDto;

import javax.sql.DataSource;
import java.sql.*;

@RequiredArgsConstructor
@Component
public class SaleManager {
    private final DataSource dataSource;

    public SaleDto save(SaleDto dto) {
        try (
                Connection connection = dataSource.getConnection();
        ) {

            try (
                    PreparedStatement statement = connection.prepareStatement(
                            "INSERT INTO  sales(manager_id) VALUES (?);",
                            Statement.RETURN_GENERATED_KEYS
                    );
            ) {
                int index = 0;
                statement.setLong(++index, dto.getManagerId());
                statement.executeUpdate();
                try {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    )
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
