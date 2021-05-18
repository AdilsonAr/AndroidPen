package com.example.pen.utility;

import android.view.View;

@FunctionalInterface
public interface IActionOnViewAtPossition {
    void action(View v, int possition);
}