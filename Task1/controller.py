import view
import operations


def start():
    print(view.menuStart())
    choose = view.answerOfUser()
    if choose == "1":
        operations.createNewNote()
        print()
        start()
    elif choose == "2":
        operations.searchNote()
        print()
        start()
    elif choose == "3":
        operations.addInfoToNote()
        print()
        start()
    elif choose == "4":
        operations.removeNote()
        print()
        start()
    elif choose == "5":
        operations.showAllNotes()
        print()
        start()
    elif choose == "E" or choose == "e":
        print("Заметки закрыты.")
    else:
        print("Введен неверный символ!")
        print()
        start()
