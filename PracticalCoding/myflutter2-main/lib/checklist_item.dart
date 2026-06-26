import 'package:flutter/material.dart';

class ChecklistItem extends StatefulWidget {
  final String text;
  ChecklistItem({required this.text});

  @override
  State<StatefulWidget> createState() {
    return _ChecklistItem();
  }
}

class _ChecklistItem extends State<ChecklistItem> {
  bool _isChecked = false;

  void _check() {
    setState(() {
      _isChecked = !_isChecked;
    });
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: _check,
      child: Container(
        padding: EdgeInsets.symmetric(horizontal: 20, vertical: 10),
        child: Text(
          widget.text,
          style: TextStyle(
            fontSize: 30,
            color: _isChecked ? Colors.grey : Colors.black,
            decoration: _isChecked ? TextDecoration.lineThrough : null,
          ),
        ),
      ),
    );
  }
}
