/*
 * Subject class - notifies observer of changes
 *
 * Last Modified - Jakob Allen, 3/9/2020
 */

package filesystem.utilities;

public interface Subject {
    //methods
    // -- registerObserver(observer) - add an observer to notify
    void registerObserver(Observer o);

    // -- unregisterObserver(observer) - remove an observer
    void unregisterObserver(Observer o);

    // -- notifyObservers() - update the observer(s)
    void notifyObservers();

}   // end - interface Subject

