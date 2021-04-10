package tech.itpark.web.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tech.itpark.web.dto.ManagerDto;
import tech.itpark.web.dto.ManagerUpdateDto;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ManagerManager {
    private final DataSource dataSource;

    public List<ManagerDto> getAll() {

        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT id, name, department, boss_id FROM managers ORDER BY id");
        ) {
            List<ManagerDto> result = new LinkedList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                long bossId = resultSet.getLong("boss_id");
                result.add(new ManagerDto(
                        id, name, department, bossId
                ));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ManagerUpdateDto save(ManagerUpdateDto dto) {
        if (dto.getId() == 0) {
            return create(dto);
        }
        return update(dto);
    }

    public ManagerUpdateDto create(ManagerUpdateDto dto) throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("""
                        INSERT INTO managers(name, salary, plan, department, boss_id)
                        VALUES (?, ?, ?, ?, ?)
                        """, Statement.RETURN_GENERATED_KEYS);
        ) {
            int index = 0;
            statement.setString(++index, dto.getName());
            statement.setInt(++index, dto.getSalary());
            statement.setInt(++index, dto.getPlan());
            statement.setString(++index, dto.getDepartment());
            statement.setLong(++index, dto.getBossId());
            statement.execute();

            try (
                    ResultSet generatedKeys = statement.getGeneratedKeys();
            ) {
                if (generatedKeys.next()) {
                    dto.setId
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

