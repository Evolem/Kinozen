package ru.gbjava.kinozen.utilites;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ru.gbjava.kinozen.validators.Annotations.Link;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

@Component
public class BreadCrumbInterceptor extends HandlerInterceptorAdapter {
    private final String BREAD_CRUMB = "breadCrumb";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Annotation[] declaredAnnotations = getDeclaredAnnotationForHandler(handler);

        HttpSession session = request.getSession();
        emptyCurrentBreadCrumbs(session);

        for (Annotation annotation : declaredAnnotations) {
            if (annotation.annotationType().equals(Link.class)) {
                processAnnotation(request, session, annotation);
            }

        }
        return true;
    }

    private void processAnnotation(HttpServletRequest request, HttpSession session, Annotation annotation) {
        Link link = (Link) annotation;
        String family = link.family();

        Map<String, LinkedHashMap<String, BreadCrumbLink>> breadCrumb = getBreadCrumbLinksFromSession(session);

        if (breadCrumb == null) {
            breadCrumb = new HashMap<>();
            session.setAttribute(BREAD_CRUMB, breadCrumb);
        }

        Map<String, BreadCrumbLink> familyMap = breadCrumb.computeIfAbsent(family, k -> new LinkedHashMap<>());

        BreadCrumbLink breadCrumbLink = getBreadCrumbLink(request, link, familyMap);
        List<BreadCrumbLink> currentBreadCrumb = new LinkedList<>();
        generateBreadCrumbRecursively(breadCrumbLink, currentBreadCrumb);
        session.setAttribute("currentBreadCrumb", currentBreadCrumb);
    }

    private void generateBreadCrumbRecursively(BreadCrumbLink link, List<BreadCrumbLink> breadCrumbLinks) {
        if (link.getPrevious() != null) {
            generateBreadCrumbRecursively(link.getPrevious(), breadCrumbLinks);
        }
        breadCrumbLinks.add(link);
    }

    private BreadCrumbLink getBreadCrumbLink(HttpServletRequest request, Link link, Map<String, BreadCrumbLink> familyMap) {
        BreadCrumbLink breadCrumbLink;
        BreadCrumbLink breadCrumbLinkObject = familyMap.get(link.label());
        resetBreadCrumbs(familyMap);

        if (breadCrumbLinkObject != null) {
            breadCrumbLinkObject.setCurrentPage(true);
            breadCrumbLink = breadCrumbLinkObject;
        } else {
            breadCrumbLink = new BreadCrumbLink(link.family(), link.label(), true, link.parent());
            String fullURL = request.getRequestURL().append((request.getQueryString() == null) ? "" : "?" + request.getQueryString()).toString();
            breadCrumbLink.setUrl(fullURL);
            createRelationships(familyMap, breadCrumbLink);
            familyMap.put(link.label(), breadCrumbLink);
        }
        return breadCrumbLink;
    }

    private void createRelationships(Map<String, BreadCrumbLink> familyMap, BreadCrumbLink newLink) {
        for (BreadCrumbLink breadCrumbLink : familyMap.values()) {
            if (breadCrumbLink.getLabel().equalsIgnoreCase(newLink.getParentKey())) {
                breadCrumbLink.addNext(newLink);
                newLink.setPrevious(breadCrumbLink);
                newLink.setParent(breadCrumbLink);
            }
        }
    }

    private void resetBreadCrumbs(Map<String, BreadCrumbLink> familyMap) {
        for (BreadCrumbLink breadCrumbLink : familyMap.values()) {
            breadCrumbLink.setCurrentPage(false);
        }
    }

    @SuppressWarnings("uncheced")
    private Map<String, LinkedHashMap<String, BreadCrumbLink>> getBreadCrumbLinksFromSession(HttpSession session) {
        return (Map<String, LinkedHashMap<String, BreadCrumbLink>>) session.getAttribute(BREAD_CRUMB);
    }

    private void emptyCurrentBreadCrumbs(HttpSession session) {
        session.setAttribute("currentBreadCrumb", new LinkedList<BreadCrumbLink>());
    }

    private Annotation[] getDeclaredAnnotationForHandler(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            return method.getDeclaredAnnotations();
        }
        return new Annotation[0];
    }
}
