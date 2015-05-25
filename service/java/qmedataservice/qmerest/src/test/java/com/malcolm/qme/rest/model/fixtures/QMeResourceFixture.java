/**
 * Name      : com.malcolm.qme.rest.model.fixtures.QMeResourceFixture.java
 * Date      : 5/25/15
 * Developer : Malcolm
 * Purpose   : Base QMe Resource Fixture
 */

package com.malcolm.qme.rest.model.fixtures;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @author Malcolm
 */
public class QMeResourceFixture<T> {

    /**
     * Object Mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     *  Convert an object to JSON using Jackson's ObjectMapper
     *
     * @param o
     * @return
     * @throws Exception
     */
    public static String toJson(Object o) throws Exception{
        return objectMapper.writeValueAsString(o);
    }

    /**
     * Convert to Resource Object from JSON String
     *
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public T toResource(String jsonStr) throws Exception{
        return  (T)objectMapper.readValue(jsonStr, new TypeReference<T>(){});
    }

    /**
     * Convert to Resource List from JSON String
     *
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<T> toResourceList(String jsonStr) throws Exception{
        return  (List<T>)objectMapper.readValue(jsonStr, new TypeReference<List<T>>(){});
    }
}
