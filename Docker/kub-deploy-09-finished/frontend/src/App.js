import React, { useState, useEffect, useCallback } from "react";

import "./App.css";
import TaskList from "./components/TaskList";
import NewTask from "./components/NewTask";

function App() {
  const [tasks, setTasks] = useState([]);
  const [token, setToken] = useState("");
  const [id, setId] = useState("");
  const [pw, setPw] = useState("");

  const fetchTasks = useCallback(
    function () {
      if (!token) return;

      fetch("/tasks", {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      })
        .then(function (response) {
          return response.json();
        })
        .then(function (jsonData) {
          setTasks(jsonData.tasks);
        });
    },
    [token]
  );

  useEffect(
    function () {
      fetchTasks();
    },
    [fetchTasks]
  );

  function addTaskHandler(task) {
    fetch("/tasks", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify(task),
    })
      .then(function (response) {
        return response.json();
      })
      .then(function (resData) {
        console.log(resData);
      });
  }

  function loginHandler() {
    fetch("/users/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ email: id, password: pw }),
    })
      .then(function (response) {
        return response.json();
      })
      .then(function (data) {
        if (data.token) {
          console.log(data);
          setToken(data.token);
        }
      })
      .catch(function (error) {
        console.error(error);
      });
  }

  function signUpHandler() {
    fetch("/users/signup", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ email: id, password: pw }),
    })
      .then(function (response) {
        return response.json();
      })
      .then(function (data) {
        console.log(data);
      })
      .catch(function (error) {
        console.error(error);
      });
  }

  return (
    <div className="App">
      <div>
        <div>
          <input
            type="text"
            value={id}
            onChange={(e) => setId(e.target.value)}
            placeholder="이메일"
            style={{ fontSize: "16px" }}
          />
        </div>
        <div>
          <input
            type="password"
            value={pw}
            onChange={(e) => setPw(e.target.value)}
            placeholder="비밀번호"
            style={{ fontSize: "16px" }}
          />
        </div>
      </div>
      <button onClick={signUpHandler} style={{ fontSize: "16px" }}>
        회원가입
      </button>
      <button onClick={loginHandler} style={{ fontSize: "16px" }}>
        로그인
      </button>

      <section>
        <NewTask onAddTask={addTaskHandler} />
      </section>
      <section>
        <button onClick={fetchTasks}>Fetch Tasks</button>
        <TaskList tasks={tasks} />
      </section>
    </div>
  );
}

export default App;
