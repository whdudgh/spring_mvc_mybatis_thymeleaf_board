function toggleBold() {
  var textarea = document.getElementById("content");
  var button = document.querySelector(".option-button.bold");
  textarea.style.fontWeight = textarea.style.fontWeight === "bold" ? "normal" : "bold";
  button.classList.toggle("active");
}

function toggleItalic() {
  var textarea = document.getElementById("content");
  var button = document.querySelector(".option-button.italic");
  textarea.style.fontStyle = textarea.style.fontStyle === "italic" ? "normal" : "italic";
  button.classList.toggle("active");
}

function toggleUnderline() {
  var textarea = document.getElementById("content");
  var button = document.querySelector(".option-button.underline");
  textarea.style.textDecoration = textarea.style.textDecoration === "underline" ? "none" : "underline";
  button.classList.toggle("active");
}

function toggleAlign(align) {
  var textarea = document.getElementById("content");
  textarea.style.textAlign = align;
  
  var buttons = document.querySelectorAll(".option-button.align-left, .option-button.align-center, .option-button.align-right");
  buttons.forEach(function(button) {
      button.classList.remove("active");
  });
  
  var activeButton = document.querySelector(".option-button.align-" + align);
  activeButton.classList.add("active");
}


function changeFontSize(change) {
  var textarea = document.getElementById("content");
  var currentSize = parseInt(window.getComputedStyle(textarea, null).getPropertyValue('font-size'));
  var newSize = currentSize + change;
  textarea.style.fontSize = newSize + "px";
}


function toggleUnderline() {
  var textarea = document.getElementById("content");
  var button = document.querySelector(".option-button.underline");
  textarea.style.textDecoration = textarea.style.textDecoration === "underline" ? "none" : "underline";
  button.classList.toggle("active");
}

// 글씨 크기 조절 함수
function changeFontSize(change) {
  var textarea = document.getElementById("content");
  var currentSize = parseInt(window.getComputedStyle(textarea, null).getPropertyValue('font-size'));
  var newSize = currentSize + change;
  textarea.style.fontSize = newSize + "px";
}

// 밑줄 토글 함수
function toggleUnderline() {
  var textarea = document.getElementById("content");
  var button = document.querySelector(".option-button.underline");
  textarea.style.textDecoration = textarea.style.textDecoration === "underline" ? "none" : "underline";
  button.classList.toggle("active");
}

// 목록 형식 변경 함수

// function toggleList(type) {
//   var textarea = document.getElementById("content");
//   var buttonBullet = document.querySelector(".option-button.list-bullet");
//   var buttonNumber = document.querySelector(".option-button.list-number");

//   if (type === 'ul') {
//       textarea.style.listStyleType = textarea.style.listStyleType === "disc" ? "none" : "disc";
//       buttonBullet.classList.toggle("active");
//       buttonNumber.classList.remove("active");
//   } else if (type === 'ol') {
//       textarea.style.listStyleType = textarea.style.listStyleType === "decimal" ? "none" : "decimal";
//       buttonNumber.classList.toggle("active");
//       buttonBullet.classList.remove("active");
//   }
// }

// 기존 스크립트 유지

function toggleList(type) {
  var textarea = document.getElementById("content");
  var buttonBullet = document.querySelector(".option-button.list-bullet");
  var buttonNumber = document.querySelector(".option-button.list-number");

  if (type === 'ul') {
      textarea.style.listStyleType = textarea.style.listStyleType === "disc" ? "none" : "disc";
      buttonBullet.classList.toggle("active");
      buttonNumber.classList.remove("active");

      if (buttonNumber.classList.contains("active")) {
          toggleList('ol');
      }
  } else if (type === 'ol') {
      textarea.style.listStyleType = textarea.style.listStyleType === "decimal" ? "none" : "decimal";
      buttonNumber.classList.toggle("active");
      buttonBullet.classList.remove("active");

      if (buttonBullet.classList.contains("active")) {
          toggleList('ul');
      }
  }
}

// 기존 스크립트 유지

function toggleStrikeThrough() {
  var textarea = document.getElementById("content");
  var button = document.querySelector(".option-button.strike-through");
  var currentStyle = window.getComputedStyle(textarea, null).getPropertyValue('text-decoration');

  if (currentStyle.includes("line-through")) {
      textarea.style.textDecoration = "none";
      button.classList.remove("active");
  } else {
      textarea.style.textDecoration = "line-through";
      button.classList.add("active");
  }
}
