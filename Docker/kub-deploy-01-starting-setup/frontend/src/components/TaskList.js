import React from "react";

import "./TaskList.css";

function TaskList(props) {
  const tasks = props.tasks || [];

  return (
    <ul>
      {tasks.map((task) => (
        <li key={task.title}>
          <h2>{task.title}</h2>
          <p>{task.text}</p>
        </li>
      ))}
    </ul>
  );
}

export default TaskList;
