package com.example.work_liuchangxu.work_0427;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.work_liuchangxu.R;

import java.util.Arrays;
import java.util.List;

public class Main0427Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view);
        setTags();
    }

    private void setTags() {
        TagCloud tagCloud = findViewById(R.id.tag_cloud);
        DragDetector dragDetector = new DragDetector(tagCloud);
        List<String> list = Arrays.asList("Java", "C++", "Python", "JavaScript", "Ruby", "Swift", "Kotlin", "Go", "Rust", "PHP", "C#", "HTML", "CSS", "SQL", "Shell", "TypeScript", "R", "Objective-C", "Scala", "Perl", "Lua", "Groovy", "Matlab", "Visual Basic", "Dart", "Assembly", "Erlang", "VHDL", "Verilog", "COBOL", "Fortran", "Ada", "Lisp", "Scheme", "Prolog", "Haskell", "Clojure", "F#", "OCaml", "Smalltalk", "ActionScript", "Pascal", "Logo", "Ada", "Lisp", "Scheme", "Prolog", "Haskell", "Clojure", "F#", "OCaml", "Smalltalk", "ActionScript", "Pascal", "Logo", "Java", "C++", "Python", "JavaScript", "Ruby", "Swift", "Kotlin", "Go", "Rust", "PHP", "C#", "HTML", "CSS", "SQL", "Shell", "TypeScript", "R", "Objective-C", "Scala", "Perl", "Lua", "Groovy", "Matlab", "Visual Basic", "Dart", "Assembly", "Erlang", "VHDL", "Verilog", "COBOL", "Fortran", "Ada", "Lisp", "Scheme", "Prolog", "Haskell", "Clojure", "F#", "OCaml", "Smalltalk", "ActionScript", "Pascal", "Logo", "Ada", "Lisp", "Scheme", "Prolog", "Haskell", "Clojure", "F#", "OCaml", "Smalltalk", "ActionScript", "Pascal", "Logo", "Java", "C++", "Python", "JavaScript", "Ruby", "Swift", "Kotlin", "Go", "Rust", "PHP", "C#", "HTML", "CSS", "SQL", "Shell", "TypeScript", "R", "Objective-C", "Scala", "Perl", "Lua", "Groovy", "Matlab", "Visual Basic", "Dart", "Assembly", "Erlang", "VHDL", "Verilog", "COBOL", "Fortran", "Ada", "Lisp", "Scheme", "Prolog", "Haskell", "Clojure", "F#", "OCaml", "Smalltalk", "ActionScript", "Pascal", "Logo", "Ada", "Lisp", "Scheme", "Prolog", "Haskell", "Clojure", "F#", "OCaml", "Smalltalk", "ActionScript", "Pascal", "Logo", "Java", "C++", "Python", "JavaScript", "Ruby", "Swift", "Kotlin", "Go", "Rust", "PHP", "C#", "HTML", "CSS", "SQL", "Shell", "TypeScript", "R", "Objective-C", "Scala", "Perl", "Lua", "Groovy", "Matlab", "Visual Basic", "Dart", "Assembly", "Erlang", "VHDL", "Verilog", "COBOL", "Fortran", "Ada", "Lisp", "Scheme", "Prolog", "Haskell", "Clojure", "F#", "OCaml", "Smalltalk", "ActionScript", "Pascal", "Logo", "Ada", "Lisp", "Scheme", "Prolog", "Haskell", "Clojure", "F#", "OCaml", "Smalltalk", "ActionScript", "Pascal", "Logo");
        tagCloud.setTags(list);

    }
}