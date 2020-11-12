package ru.telda.dds_01_integration_service.util.mybatisHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(JsonNode.class)
public class JsonTypeHandler extends BaseTypeHandler<JsonNode> {

    @Autowired
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JsonNode node, JdbcType jdbcType) throws SQLException {
        String json = null;
        try {
            json = mapper.writeValueAsString(node);
            ps.setString(i, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    @Override
    public JsonNode getNullableResult(ResultSet resultSet, String s) throws SQLException {
        JsonNode node = null;
        try {
            node = mapper.readTree(resultSet.getString(s));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return node;
    }

    @Override
    public JsonNode getNullableResult(ResultSet resultSet, int i) throws SQLException {
        JsonNode node = null;
        try {
            node = mapper.readTree(resultSet.getString(i));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return node;
    }

    @Override
    public JsonNode getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        JsonNode node = null;
        try {
            node = mapper.readTree(callableStatement.getString(i));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return node;
    }
}
