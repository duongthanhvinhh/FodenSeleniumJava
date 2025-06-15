package org.foden.api.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.foden.utils.Log4jUtils;

public class LoggingFilter implements Filter {
    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {
        logRequest(filterableRequestSpecification);
        Response response = filterContext.next(filterableRequestSpecification, filterableResponseSpecification); //Request is going to be executed over here
        logResponse(response);
        return response;
    }

    public void logRequest(FilterableRequestSpecification filterableRequestSpecification){
        Log4jUtils.info("Base URI: " + filterableRequestSpecification.getBaseUri());
        Log4jUtils.info("Request Headers: " + filterableRequestSpecification.getHeaders());
        Log4jUtils.info("Request Body: " + filterableRequestSpecification.getBody());
    }

    public void logResponse(Response response){
        Log4jUtils.info("Status code: " + response.getStatusCode());
        Log4jUtils.info("Response Headers: " + response.getHeaders());
        Log4jUtils.info("Response Body: " + response.getBody().asPrettyString());
    }
}
