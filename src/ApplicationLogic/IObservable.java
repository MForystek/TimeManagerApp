package ApplicationLogic;

public interface IObservable {
    void addObserver(IObserver iObserver);
    void notifyObserver();
}
