__author__ = 'Sam'

class Observable(object):
    def __init__(self):
        super(Observable, self).__init__()
        self._observers = []
    def addObserver(self, observer):
        if not observer in self._observers:
            self._observers.append(observer)
    def removeObserver(self, observer):
        try:
            self._observers.remove(observer)
        except ValueError:
            pass
    def notifyObservers(self,army):
        for observer in self._observers:
            observer.update(army)

class Observer(object):
    def __init__(self):
        super(Observer, self).__init__()
    def update(self, observable):
        pass



