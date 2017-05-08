package sc;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionCounter In order
 * to avoid conflicts with multiple threads access to private static varieable
 * in this class, methods attempting to make a change use synchronize().
 */
@WebListener
public class SessionCounter implements HttpSessionListener {

	private static int active = 0;

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 * When session is created this method is called to inrement active sessions counter.
	 */
	public void sessionCreated(HttpSessionEvent arg0) {
		synchronized (arg0.getSession()) {
			active++;
		}
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 * When session is invalidated this method is called to decrement active sessions counter.
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) {
		if (active > 0)
			synchronized (arg0.getSession()) {
				active--;
			}
	}

	/**
	 * @return Number of active sessions.
	 */
	public static int getActiveSessions() {
		return active;
	}

}
