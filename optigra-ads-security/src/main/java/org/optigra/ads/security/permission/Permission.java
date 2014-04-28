package org.optigra.ads.security.permission;

/**
 * Permission check
 *
 * @date Apr 16, 2014
 * @author Iurii Parfeniuk
 * @param <T>
 *            type of check context
 */
public interface Permission<T> {

    void check(T context);
}
