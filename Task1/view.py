import operations


def nameOfNote():
    return input("Введите название заметки: ")


def textOfNote():
    return input("Введите текст заметки: ")


def nameOfFile():
    name = nameOfNote()
    file_name = name + '.txt'
    return file_name


def viewNotes():
    print()
    operations.showAllNotes()
    print()


def menuStart():
    return ("Введите номер для выбора действия либо 'e', чтобы выйти:\n"
            "1. Создать заметку\n"
            "2. Найти и показать заметку\n"
            "3. Добавить информацию в заметку\n"
            "4. Удалить заметку\n"
            "5. Показать список заметок")
            


def answerOfUser():
    return input("Выбрано: ")


def NoneExistNote():
    print("Такой заметки не существует!")
    print("Введите e, если хотите выйти в основное меню")
