package io.github.jjyy.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jy
 */
public class HttpRequestFilterChain {

    private final List<HttpRequestFilter> filters = new ArrayList<>();

    public HttpRequestFilterChain addFilter(HttpRequestFilter filter) {
        filters.add(filter);
        return this;
    }

    public void removeAll() {
        filters.clear();
    }

    public List<HttpRequestFilter> getFilters() {
        return filters;
    }
}
