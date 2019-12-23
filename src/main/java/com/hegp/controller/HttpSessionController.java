package com.hegp.controller;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class HttpSessionController {
    @GetMapping("/test/{id}")
    public Map test(@PathVariable(name = "id") String id,
                    HttpServletRequest request) {
        AntPathMatcher aa;
        id = request.getSession(true).getId();
        System.out.println(id);
        Map map = new HashMap();
        map.put("id", id);
        return map;
    }

//    @DeleteMapping("/test/1")
    @PostMapping("/test/1")
    public Map test22(HttpServletRequest request) {
        AntPathMatcher aa;
        String id = request.getSession(true).getId();
        System.out.println(id);
        Map map = new HashMap();
        map.put("id", id);
        return map;
    }

    @GetMapping("/test/{id1}/12/")
    public Map test122(@PathVariable(name = "id1") String id1,
                     HttpServletRequest request) {
        RequestMappingInfo a;
        String sessionId = request.getSession(true).getId();
        System.out.println(sessionId);
        Map map = new HashMap();
        map.put("id", sessionId);
        return map;
    }

    @GetMapping("/test/{id1}/{id2}")
    public Map test1(@PathVariable(name = "id1") String id1,
                     @PathVariable(name = "id2") String id2,
                     HttpServletRequest request) {
        RequestMappingInfo a;
        String sessionId = request.getSession(true).getId();
        System.out.println(sessionId);
        Map map = new HashMap();
        map.put("id", sessionId);
        return map;
    }

    @RequestMapping(value = {"/test/{id3}/{id2}", "/aaa"})
    public Map test12(@PathVariable(name = "id3") String id3,
                      @PathVariable(name = "id2") String id2,
                      HttpServletRequest request) {
        String sessionId = request.getSession(true).getId();
        System.out.println(sessionId);
        Map map = new HashMap();
        map.put("id", sessionId);
        return map;
    }

//    /**
//     * springmvc官方的URL匹配逻辑，除非有朝一天你自认为实现的比官方还好，再去重写
//     * Look up the best-matching handler method for the current request.
//     * If multiple matches are found, the best match is selected.
//     * @param lookupPath mapping lookup path within the current servlet mapping
//     * @param request the current request
//     * @return the best-matching handler method, or {@code null} if no match
//     * @see #handleMatch(Object, String, HttpServletRequest)
//     * @see #handleNoMatch(Set, String, HttpServletRequest)
//     */
//    protected String lookupHandlerMethod(String lookupPath, HttpServletRequest request) throws Exception {
//        List<Match> matches = new ArrayList<>();
//        List<T> directPathMatches = this.mappingRegistry.getMappingsByUrl(lookupPath);
//        if (directPathMatches != null) {
//            addMatchingMappings(directPathMatches, matches, request);
//        }
//        if (matches.isEmpty()) {
//            // No choice but to go through all mappings...
//            addMatchingMappings(this.mappingRegistry.getMappings().keySet(), matches, request);
//        }
//
//        if (!matches.isEmpty()) {
//            Comparator<Match> comparator = new MatchComparator(getMappingComparator(request));
//            matches.sort(comparator);
//            Match bestMatch = matches.get(0);
//            if (matches.size() > 1) {
//                if (logger.isTraceEnabled()) {
//                    logger.trace(matches.size() + " matching mappings: " + matches);
//                }
//                if (CorsUtils.isPreFlightRequest(request)) {
//                    return PREFLIGHT_AMBIGUOUS_MATCH;
//                }
//                Match secondBestMatch = matches.get(1);
//                if (comparator.compare(bestMatch, secondBestMatch) == 0) {
//                    Method m1 = bestMatch.handlerMethod.getMethod();
//                    Method m2 = secondBestMatch.handlerMethod.getMethod();
//                    String uri = request.getRequestURI();
//                    throw new IllegalStateException(
//                            "Ambiguous handler methods mapped for '" + uri + "': {" + m1 + ", " + m2 + "}");
//                }
//            }
//            request.setAttribute(BEST_MATCHING_HANDLER_ATTRIBUTE, bestMatch.handlerMethod);
//            handleMatch(bestMatch.mapping, lookupPath, request);
//            return bestMatch.handlerMethod;
//        }
//        else {
//            return handleNoMatch(this.mappingRegistry.getMappings().keySet(), lookupPath, request);
//        }
//    }
}
