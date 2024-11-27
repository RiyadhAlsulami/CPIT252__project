
abstract class Observable {
    private java.util.List<Observer> observers = new java.util.ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

interface Observer {
    void update(String message);
}
