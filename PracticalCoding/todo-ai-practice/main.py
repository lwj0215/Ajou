"""
간단한 콘솔 기반 할 일 관리 프로그램
이 버전은 todos.json 기반 자동 저장 및 로드를 포함한다.
"""

import json
from pathlib import Path

DATA_FILE = Path(__file__).resolve().parent / 'todos.json'
todos = []  # 할 일 목록을 담는 리스트. 각 항목은 {'task': str, 'progress': str}


def print_menu():
    # 사용자에게 보여줄 메뉴를 출력하는 함수
    print("==== 할 일 관리 프로그램 ====")
    print("1. 할 일 추가")
    print("2. 할 일 목록 보기")
    print("3. 할 일 완료 처리")
    print("4. 할 일 삭제")
    print("5. 종료")


def load_todos():
    # todos.json 파일에서 데이터를 로드한다
    global todos
    if not DATA_FILE.exists():
        todos = []
        return
    try:
        with DATA_FILE.open('r', encoding='utf-8') as file:
            data = json.load(file)
    except FileNotFoundError:
        todos = []
        return
    except json.JSONDecodeError:
        print('todos.json 파일을 읽는 중 형식 오류가 발생했습니다. 기존 데이터를 무시하고 새로 시작합니다.')
        todos = []
        return
    except OSError as error:
        print(f'파일을 읽는 중 오류가 발생했습니다: {error}')
        todos = []
        return

    if not isinstance(data, list):
        print('todos.json 파일 형식이 잘못되었습니다. 기존 데이터를 무시하고 새로 시작합니다.')
        todos = []
        return

    valid_items = []
    for item in data:
        if isinstance(item, dict):
            task = item.get('task')
            progress = item.get('progress')
            if isinstance(task, str) and isinstance(progress, str):
                if progress not in ('미완료', '완료'):
                    progress = '미완료'
                valid_items.append({'task': task, 'progress': progress})
        # 문자열 리스트 형식이 있으면 무시한다.
    todos = valid_items


def save_todos():
    # 현재 todos를 todos.json 파일에 저장한다
    try:
        with DATA_FILE.open('w', encoding='utf-8') as file:
            json.dump(todos, file, ensure_ascii=False, indent=4)
    except OSError as error:
        print(f'파일을 저장하는 중 오류가 발생했습니다: {error}')


def prompt_int(prompt, min_value, max_value):
    # 정수 입력을 안전하게 받는 함수
    while True:
        value = input(prompt).strip()
        if not value:
            print('입력이 비어 있습니다. 숫자를 다시 입력하세요.')
            continue
        if not value.isdigit():
            print('숫자를 입력하세요.')
            continue
        number = int(value)
        if number < min_value or number > max_value:
            print(f'{min_value}에서 {max_value} 사이의 번호를 입력하세요.')
            continue
        return number


def add_todo():
    # 사용자로부터 제목을 입력받아 할 일을 추가한다
    while True:
        title = input('할 일 제목을 입력하세요: ').strip()
        if not title:
            print('할 일 제목은 비워둘 수 없습니다.')
            continue
        if len(title) > 200:
            print('할 일 제목은 200자 이하여야 합니다.')
            continue
        todos.append({'task': title, 'progress': '미완료'})
        save_todos()
        print('할 일이 추가되었습니다.')
        return


def list_todos():
    # 현재 등록된 할 일 목록을 출력한다
    print('---- 할 일 목록 ----')
    if len(todos) == 0:
        print('등록된 할 일이 없습니다.')
        return
    for i, item in enumerate(todos, start=1):
        print(f"{i}. [{item['progress']}] {item['task']}")


def complete_todo():
    # 번호를 입력받아 해당 할 일을 완료 처리한다
    if len(todos) == 0:
        print('먼저 할 일을 추가한 후 시도하세요.')
        return
    list_todos()
    num = prompt_int('완료할 할 일 번호를 입력하세요: ', 1, len(todos))
    todo = todos[num - 1]
    if todo['progress'] == '완료':
        print('이미 완료된 할 일입니다.')
    else:
        todo['progress'] = '완료'
        save_todos()
        print('할 일이 완료 처리되었습니다.')


def delete_todo():
    # 번호를 입력받아 해당 할 일을 삭제한다
    if len(todos) == 0:
        print('삭제할 할 일이 없습니다.')
        return
    list_todos()
    num = prompt_int('삭제할 할 일 번호를 입력하세요: ', 1, len(todos))
    removed = todos.pop(num - 1)
    save_todos()
    print(f"'{removed['task']}' 할 일이 삭제되었습니다.")


def main():
    # 시작 시 파일에서 할 일을 로드하고 메인 루프를 실행한다
    load_todos()
    while True:
        print_menu()
        choice = input('메뉴를 선택하세요: ').strip()
        if choice == '1':
            add_todo()
        elif choice == '2':
            list_todos()
        elif choice == '3':
            complete_todo()
        elif choice == '4':
            delete_todo()
        elif choice == '5':
            print('프로그램을 종료합니다.')
            break
        else:
            print('잘못된 선택입니다. 1에서 5 사이의 번호를 입력하세요.')


if __name__ == '__main__':
    main()
