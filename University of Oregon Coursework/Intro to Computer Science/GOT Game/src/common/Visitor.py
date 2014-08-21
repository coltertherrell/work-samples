__author__ = 'Sam'

class IVisitable(object):
    pass

    def accept(self):
        pass


class IVisitor(object):
    pass

    def visit(self):
        pass