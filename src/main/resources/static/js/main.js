window.document.body.onload = () => {
  document.getElementById("submit_button").onclick = () => {
    const kotlin_code = document.getElementById("kotlin_code").value;
    console.log("kotlin_code", kotlin_code);
    fetch("kotlinc", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ code: kotlin_code, version: "123" }),
    })
      .then((response) => response.json())
      .then((data) => {
        document.getElementById("byte_code").value = data.byteCode;
      });
  };
};
