import view
import operations
import controller
import os


def createNewNote():
    file_name = view.nameOfFile()
    if os.path.isfile(file_name):
        print("Заметка с таким именем существует!")
        createNewNote()
    elif (file_name.replace(".txt", "")) == "e" or (file_name.replace(".txt", "")) == "E":
        print("Данное имя недопустимо!")
        createNewNote()
    else:
        with open(file_name, 'w', encoding='utf-8') as file:
            file.write(view.textOfNote())
        with open("all_notes.txt", 'a', encoding='utf-8') as file:
            file.write(file_name.replace(".txt", "") + "\n")


def searchNote():
    view.viewNotes()
    file_name = view.nameOfFile()
    if (file_name.replace(".txt", "")) == "e" or (file_name.replace(".txt", "")) == "E":
        controller.start()
    elif os.path.isfile(file_name):
        with open(file_name, 'r', encoding='utf-8') as file:
            for line in file:
                print(line.strip())
    else:
        view.NonExistNote()
        searchNote()


def addInfoToNote():
    view.viewNotes()
    file_name = view.nameOfFile()
    if (file_name.replace(".txt", "")) == "e" or (file_name.replace(".txt", "")) == "E":
        controller.start()
    elif os.path.isfile(file_name):
        with open(file_name, 'a', encoding='utf-8') as file:
            file.write(view.textOfNote())
        print("Заметка добавлена!")
    else:
        view.NonExistNote()
        addInfoToNote()


def showAllNotes():
    print("\nСписок всех заметок:")
    with open("all_notes.txt", 'r', encoding='utf-8') as file:
        for line in file:
            print(line.strip())


def removeNote():
    view.viewNotes()
    file_name = view.nameOfFile()
    if (file_name.replace(".txt", "")) == "e" or (file_name.replace(".txt", "")) == "E":
        controller.start()
    elif os.path.isfile(file_name):
        with open('all_notes.txt', 'r', encoding='utf-8') as file:
            lines = file.readlines()
            with open('all_notes.txt', 'w', encoding='utf-8') as file:
                for line in lines:
                    if line != (file_name.replace(".txt", "") + "\n"):
                        file.write(line)
        os.remove(file_name)
        print("Заметка удалена!")
    else:
        view.NonExistNote()
        removeNote()
