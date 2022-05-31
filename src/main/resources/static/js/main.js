window.document.body.onload = () => {
  document.getElementById("submit_button").onclick = () => {
    const kotlin_code = document.getElementById("kotlin_code").value;
    console.log("kotlin_code", kotlin_code);

    fetch("http://localhost:8080/kotlinc", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ code: kotlin_code, version: "123" }),
    })
      .then((response) => response.json())
      .then((data) => console.log(data));
  };
};
