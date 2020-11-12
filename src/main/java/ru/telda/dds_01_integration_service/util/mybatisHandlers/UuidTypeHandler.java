package ru.telda.dds_01_integration_service.util.mybatisHandlers;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@MappedJdbcTypes(JdbcType.OTHER)
@MappedTypes(UUID.class)
public class UuidTypeHandler extends BaseTypeHandler<UUID> {

    @Override
    public void setParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType) throws SQLException {
        if (parameter==null)
            System.out.println("");
        super.setParameter(ps, i, parameter, jdbcType);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UUID uuid, JdbcType jdbcType) throws SQLException {
        ps.setObject(i,uuid,jdbcType.TYPE_CODE);

    }

    @Override
    public UUID getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return (UUID) resultSet.getObject(s);
    }

    @Override
    public UUID getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return (UUID) resultSet.getObject(i);
    }

    @Override
    public UUID getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return (UUID) callableStatement.getObject(i);
    }
}