"""
간단한 콘솔 기반 할 일 관리 프로그램
이 버전은 todos.json 기반 자동 저장 및 로드를 포함한다.
"""

import json
from pathlib import Path

DATA_FILE = Path(__file__).resolve().parent / 'todos.json'
todos = []  # 각 항목 형식: {'task': str, 'progress': str}


def print_menu():
    print("==== 할 일 관리 프로그램 ====")
    print("1. 할 일 추가")
    print("2. 할 일 목록 보기")
    print("3. 할 일 완료 처리")
    print("4. 할 일 삭제")
    print("5. 종료")


def load_todos():
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

    loaded_todos = []

    for item in data:
        if not isinstance(item, dict):
            continue

        task = item.get('task')
        progress = item.get('progress')

        if not isinstance(task, str):
            continue
        if not isinstance(progress, str):
            continue

        if progress not in ('미완료', '완료'):
            progress = '미완료'

        loaded_todos.append({
            'task': task,
            'progress': progress
        })

    todos = loaded_todos


def save_todos():
    try:
        with DATA_FILE.open('w', encoding='utf-8') as file:
            json.dump(todos, file, ensure_ascii=False, indent=4)
    except OSError as error:
        print(f'파일을 저장하는 중 오류가 발생했습니다: {error}')


def show_todos():
    print('---- 할 일 목록 ----')

    if len(todos) == 0:
        print('등록된 할 일이 없습니다.')
        return

    for index, item in enumerate(todos, start=1):
        print(f"{index}. [{item['progress']}] {item['task']}")


def get_todo_number(prompt):
    if len(todos) == 0:
        print('등록된 할 일이 없습니다.')
        return None

    while True:
        user_input = input(prompt).strip()

        if user_input == '':
            print('입력이 비어 있습니다. 번호를 다시 입력하세요.')
            continue

        if not user_input.isdigit():
            print('숫자 번호를 입력하세요.')
            continue

        number = int(user_input)

        if number < 1 or number > len(todos):
            print(f'1에서 {len(todos)} 사이의 번호를 입력하세요.')
            continue

        return number


def add_todo():
    while True:
        task = input('할 일 제목을 입력하세요: ').strip()

        if task == '':
            print('할 일 제목은 비워둘 수 없습니다.')
            continue

        todos.append({
            'task': task,
            'progress': '미완료'
        })
        save_todos()
        print('할 일이 추가되었습니다.')
        return


def complete_todo():
    if len(todos) == 0:
        print('먼저 할 일을 추가한 후 시도하세요.')
        return

    show_todos()
    number = get_todo_number('완료할 할 일 번호를 입력하세요: ')

    if number is None:
        return

    selected_todo = todos[number - 1]

    if selected_todo['progress'] == '완료':
        print('이미 완료된 할 일입니다.')
        return

    selected_todo['progress'] = '완료'
    save_todos()
    print('할 일이 완료 처리되었습니다.')


def delete_todo():
    if len(todos) == 0:
        print('삭제할 할 일이 없습니다.')
        return

    show_todos()
    number = get_todo_number('삭제할 할 일 번호를 입력하세요: ')

    if number is None:
        return

    removed_todo = todos.pop(number - 1)
    save_todos()
    print(f"'{removed_todo['task']}' 할 일이 삭제되었습니다.")


def main():
    load_todos()

    while True:
        print_menu()
        choice = input('메뉴를 선택하세요: ').strip()

        if choice == '1':
            add_todo()
        elif choice == '2':
            show_todos()
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


# -----------------------------------------------------------------------------
# | 개선 항목 | 적용 내용 |
# |----------|-----------|
# | 메뉴 출력 분리 | print_menu() 함수로 메뉴 출력만 담당하도록 분리 |
# | 파일 불러오기 분리 | load_todos() 함수에서 todos.json 로드 처리 |
# | 파일 저장 분리 | save_todos() 함수에서 저장 처리 |
# | 할 일 추가 분리 | add_todo() 함수에서 입력 검증과 추가 처리 |
# | 할 일 목록 보기 분리 | list_todos() 대신 show_todos()로 이름을 더 명확하게 변경 |
# | 완료 처리 분리 | complete_todo() 함수에서 완료 처리 담당 |
# | 삭제 처리 분리 | delete_todo() 함수에서 삭제 처리 담당 |
# | 번호 입력 검증 분리 | get_todo_number() 함수에서 숫자 여부, 범위 여부 검사 |
# | 문자 입력 예외 대응 | 숫자 대신 문자를 입력해도 종료되지 않고 다시 입력받음 |
# | 없는 번호 예외 대응 | 존재하지 않는 번호를 입력해도 종료되지 않고 다시 입력받음 |
# | 빈 할 일 입력 방지 | 공백 또는 빈 문자열은 추가되지 않도록 처리 |
# | 함수 이름 개선 | show_todos(), get_todo_number()처럼 역할이 드러나는 이름 사용 |
# | 중복 코드 감소 | 번호 검증 로직을 get_todo_number()로 공통 처리 |
# | 기존 기능 유지 | 추가, 조회, 완료, 삭제, 종료 및 JSON 저장 형식 유지 |
# | JSON 형식 유지 | todos.json은 기존과 동일하게 리스트 안에 {'task', 'progress'} 형식 유지 |
# | 추가 개선 제안 | 현재는 전역 변수 todos를 사용 중이므로, 초급 학습 후에는 함수 인자로 전달하는 구조로 개선 가능 |
# -----------------------------------------------------------------------------
