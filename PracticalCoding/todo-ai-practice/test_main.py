import builtins
import json
import pytest
import main


@pytest.fixture(autouse=True)
def reset_todos_json():
    # Arrange: 매 테스트 시작 전에 todos.json을 빈 상태로 초기화
    main.DATA_FILE.write_text("[]", encoding="utf-8")
    main.todos.clear()
    yield
    # Arrange: 테스트 종료 후에도 상태를 비워 다음 테스트에 영향이 없게 한다
    main.DATA_FILE.write_text("[]", encoding="utf-8")
    main.todos.clear()


def test__tc_add_01_add_task_to_empty_list(capsys, monkeypatch):
    # Arrange: TC-ADD-01 정상 시나리오 - 빈 목록에 할 일을 추가한다
    monkeypatch.setattr(builtins, "input", lambda prompt: "장보기")
    # Act
    main.add_todo()
    # Assert
    assert main.todos == [{"task": "장보기", "progress": "미완료"}]
    captured = capsys.readouterr()
    assert "할 일이 추가되었습니다." in captured.out
    with main.DATA_FILE.open("r", encoding="utf-8") as file:
        assert json.load(file) == main.todos


def test__tc_add_02_add_task_when_existing_item_present(capsys, monkeypatch):
    # Arrange: TC-ADD-02 정상 시나리오 - 기존 항목이 있는 상태에서 새 할 일을 추가한다
    main.todos.append({"task": "기존 할 일", "progress": "미완료"})
    main.save_todos()
    monkeypatch.setattr(builtins, "input", lambda prompt: "스터디 자료 준비")
    # Act
    main.add_todo()
    # Assert
    assert main.todos == [
        {"task": "기존 할 일", "progress": "미완료"},
        {"task": "스터디 자료 준비", "progress": "미완료"},
    ]
    captured = capsys.readouterr()
    assert "할 일이 추가되었습니다." in captured.out


def test__tc_add_03_empty_string_input_rejected_and_retry(capsys, monkeypatch):
    # Arrange: TC-ADD-03 실패 시나리오 - 빈 문자열 입력을 거부한다
    responses = iter(["", "장보기"])
    monkeypatch.setattr(builtins, "input", lambda prompt: next(responses))
    # Act
    main.add_todo()
    # Assert
    assert main.todos == [{"task": "장보기", "progress": "미완료"}]
    captured = capsys.readouterr()
    assert "할 일 제목은 비워둘 수 없습니다." in captured.out
    assert "할 일이 추가되었습니다." in captured.out


def test__tc_add_04_whitespace_input_rejected_and_retry(capsys, monkeypatch):
    # Arrange: TC-ADD-04 실패 시나리오 - 공백만 입력한 경우를 거부한다
    responses = iter(["   ", "장보기"])
    monkeypatch.setattr(builtins, "input", lambda prompt: next(responses))
    # Act
    main.add_todo()
    # Assert
    assert main.todos == [{"task": "장보기", "progress": "미완료"}]
    captured = capsys.readouterr()
    assert "할 일 제목은 비워둘 수 없습니다." in captured.out
    assert "할 일이 추가되었습니다." in captured.out


def test__tc_add_05_min_length_title_is_accepted(capsys, monkeypatch):
    # Arrange: TC-ADD-05 경계 시나리오 - 길이 1인 제목을 입력한다
    monkeypatch.setattr(builtins, "input", lambda prompt: "a")
    # Act
    main.add_todo()
    # Assert
    assert main.todos == [{"task": "a", "progress": "미완료"}]
    captured = capsys.readouterr()
    assert "할 일이 추가되었습니다." in captured.out


def test__tc_add_06_exactly_200_characters_is_accepted(capsys, monkeypatch):
    # Arrange: TC-ADD-06 경계 시나리오 - 정확히 200자의 제목을 입력한다
    title = "가" * 200
    monkeypatch.setattr(builtins, "input", lambda prompt: title)
    # Act
    main.add_todo()
    # Assert
    assert main.todos == [{"task": title, "progress": "미완료"}]
    captured = capsys.readouterr()
    assert "할 일이 추가되었습니다." in captured.out


def test__tc_add_07_surrounding_spaces_are_trimmed(capsys, monkeypatch):
    # Arrange: TC-ADD-07 경계 시나리오 - 앞뒤 공백이 포함된 제목을 입력한다
    monkeypatch.setattr(builtins, "input", lambda prompt: "  운동하기  ")
    # Act
    main.add_todo()
    # Assert
    assert main.todos == [{"task": "운동하기", "progress": "미완료"}]
    captured = capsys.readouterr()
    assert "할 일이 추가되었습니다." in captured.out


def test__tc_add_08_save_error_raises_oserror(capsys, monkeypatch):
    # Arrange: TC-ADD-08 추가 제안 - 저장 단계에서 OSError가 발생하는 상황을 모의한다
    monkeypatch.setattr(builtins, "input", lambda prompt: "장보기")

    def failing_save_todos():
        print("파일을 저장하는 중 오류가 발생했습니다: disk full")
        raise OSError("disk full")

    monkeypatch.setattr(main, "save_todos", failing_save_todos)
    # Act
    with pytest.raises(OSError, match="disk full"):
        main.add_todo()
    # Assert
    assert main.todos == [{"task": "장보기", "progress": "미완료"}]
    captured = capsys.readouterr()
    assert "파일을 저장하는 중 오류가 발생했습니다: disk full" in captured.out


def test__tc_add_09_over_200_characters_is_rejected(capsys, monkeypatch):
    # Arrange: TC-ADD-09 추가 제안 - 200자를 초과하는 제목을 입력한다
    over_limit_title = "가" * 201
    valid_title = "가" * 200
    responses = iter([over_limit_title, valid_title])
    monkeypatch.setattr(builtins, "input", lambda prompt: next(responses))
    # Act
    main.add_todo()
    # Assert
    assert main.todos == [{"task": valid_title, "progress": "미완료"}]
    captured = capsys.readouterr()
    assert "할 일 제목은 200자 이하여야 합니다." in captured.out
    assert "할 일이 추가되었습니다." in captured.out
