import React, { useState, useEffect, useCallback } from "react";

import "./App.css";
import TaskList from "./components/TaskList";
import NewTask from "./components/NewTask";

function App() {
  const [tasks, setTasks] = useState([]);
  const [token, setToken] = useState("");

  const fetchTasks = useCallback(
    function () {
      if (!token) return;

      fetch("/api/tasks", {
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
    [fetchTasks, token]
  );

  function addTaskHandler(task) {
    fetch("/api/tasks", {
      // api로 시작하는 경로는 리버스 프록시에 전달
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

  return (
    <div className="App">
      <input
        type="text"
        value={token}
        onChange={(e) => setToken(e.target.value)}
        placeholder="token"
        style={{ fontSize: "16px" }}
      />
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
