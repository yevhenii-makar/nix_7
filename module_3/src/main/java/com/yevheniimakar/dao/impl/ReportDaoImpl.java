package com.yevheniimakar.dao.impl;

import com.yevheniimakar.config.DataSource;
import com.yevheniimakar.dao.ReportDao;
import com.yevheniimakar.entity.Report;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class ReportDaoImpl implements ReportDao {

    private static final Logger log = LoggerFactory.getLogger(ReportDaoImpl.class);


    private final String SQL_SELECT_OPERATION_FOR_PERIOD = "SELECT operations.value, " +
            "operations.date, " +
            "consumptioncategories.name as consumptioncategories_name, " +
            "incomecategories.name as incomecategories_name, " +
            "accounts.name as accounts_name, " +
            "accounts.accountsnumber " +
            "FROM operations " +
            "JOIN accounts ON operations.account_id=accounts.id " +
            "LEFT JOIN consumptioncategories ON operations.category_id = consumptioncategories.id " +
            "LEFT JOIN incomecategories ON operations.category_id = incomecategories.id " +
            "WHERE accounts.id = ? " +
            "AND operations.date BETWEEN ? AND ? " +
            "ORDER BY operations.date ";

    SessionFactory sessionFactory;

    public ReportDaoImpl() {
        sessionFactory = DataSource.getSessionFactory();
    }

    @Override
    public List<Report> getOperationReportForPeriodByAccount(Long accountDto, Instant star, Instant end) {
        List<Report> operationList = new ArrayList<>();

        sessionFactory.openSession().doWork(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_OPERATION_FOR_PERIOD)) {
                preparedStatement.setLong(1, accountDto);
                String timestamp = Timestamp.from(star).toString();
                preparedStatement.setTimestamp(2, Timestamp.from(star));
                preparedStatement.setTimestamp(3, Timestamp.from(end));
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    Report report = new Report();
                    String s = rs.getString("incomecategories_name");
                    String sss = rs.getString("consumptioncategories_name");
                    report.setAccount(rs.getString("accounts_name"));
                    String category = rs.getString("incomecategories_name") != null
                            ? rs.getString("incomecategories_name")
                            : rs.getString("consumptioncategories_name");
                    report.setCategory(category);
                    report.setDate(rs.getTimestamp("date").toInstant());
                    report.setValue(rs.getLong("value"));
                    operationList.add(report);
                }
            } catch (SQLException throwables) {
                log.error("Error", throwables);
            }

        });
        return operationList;
    }

    private String getFormattedDateTimeFromInstant(Instant instant) {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return formatter.format(instant.atZone(ZoneId.systemDefault()).toLocalDateTime());
    }


}
