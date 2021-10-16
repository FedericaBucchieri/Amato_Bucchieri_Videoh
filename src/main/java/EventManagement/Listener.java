package EventManagement;

public interface Listener {
    /**
     * The listen method is overwritten from all classes that implement this interface. Each class will handle the Event that they receive and will act accordingly
     * @param event generic event received from a dispatch method of some other classes. This event can be extended in other subclasses in order to implement the polimorphysm.
     */
    void listen(Event event);
}
