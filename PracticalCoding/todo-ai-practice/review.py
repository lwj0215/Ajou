from pathlib import Path
from openai import OpenAI
from dotenv import load_dotenv

load_dotenv()

client = OpenAI()
code = Path("main.py").read_text(encoding="utf-8")

prompt = f"""
너는 Python 코드 리팩토링을 한다.
하단의 사항을 포함하여 리팩토링할 것.
리팩토링 목표:
1. 기존 기능은 그대로 유지한다.
2. 초급자가 이해하기 쉬운 구조로 바꾼다.
3. 중복 코드를 줄인다.
4. 잘못된 입력에도 프로그램이 종료되지 않게 한다.
5. 함수 이름만 봐도 역할을 알 수 있게 한다.

반영할 개선 항목:
1. 메뉴 출력은 print_menu() 함수로 분리한다.
2. 파일 불러오기는 load_todos() 함수로 분리한다.
3. 파일 저장은 save_todos() 함수로 분리한다.
4. 할 일 추가는 add_todo() 함수로 분리한다.
5. 할 일 목록 보기는 show_todos() 함수로 분리한다.
6. 완료 처리는 complete_todo() 함수로 분리한다.
7. 삭제 처리는 delete_todo() 함수로 분리한다.
8. 번호 입력 검증은 get_todo_number() 함수로 분리한다.
9. 숫자 대신 문자를 입력해도 프로그램이 종료되지 않게 한다.
10. 존재하지 않는 번호를 입력해도 프로그램이 종료되지 않게 한다.
11. 빈 할 일은 추가되지 않게 한다.

제한 조건:
- 클래스를 사용하지 마.
- 데이터베이스를 사용하지 마.
- 웹 화면을 만들지 마.
- 외부 라이브러리를 추가하지 마.
- 초급자가 이해하기 어려운 고급 문법은 피한다.
- 기존 콘솔 기반 프로그램 구조를 유지한다.
- 기존 todos.json 파일 출력형식은 유지하면서, 코드 개선 방향을 제안한다.

출력 형식:
1. 먼저 그대로 붙여넣을 수 있는 Python 코드만 제공할 것. 출력을 즉시 파이썬 파일로 piping할 예정.
2. 코드 블록 하나로 전체 코드를 작성해줘.
3. 코드 뒤에는 개선된 점을 주석을 사용하여 표로 정리해줘.

아래 코드를 리팩토링할것.
```python
{code}
```
"""

response = client.responses.create(
    model="gpt-5.4",
    input=prompt
)

print(response.output_text)
