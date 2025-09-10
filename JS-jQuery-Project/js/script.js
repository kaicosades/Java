$(document).ready(function () {
  $("#buttonCalc").click(function () {
    let input = $("#inputNumbers").val().trim();

    if (input === "") {
      $("#pResult").text("Ошибка: введите числа.");
      return;
    }

    let numbers = input.split(",").map((num) => Number(num.trim()));

    if (numbers.some(isNaN)) {
      $("#pResult").text("Ошибка: вводите только числа через запятую.");
      return;
    }

    let max = Math.max(...numbers);
    let min = Math.min(...numbers);

    $("#pResult").text(`Максимум: ${max}, Минимум: ${min}`);
  });
});
