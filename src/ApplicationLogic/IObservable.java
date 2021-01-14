package ApplicationLogic;

public interface IObservable {
    void setObserver(IObserver iObserver);
    void notifyObserver();
}
