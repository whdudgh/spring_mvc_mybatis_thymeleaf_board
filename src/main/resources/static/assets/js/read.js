// script.js
document.addEventListener("DOMContentLoaded", function() {
  const replyButtons = document.querySelectorAll(".reply-btn");

  replyButtons.forEach(button => {
      button.addEventListener("click", function() {
          const parentComment = this.closest(".comment");
          const repliesContainer = parentComment.querySelector(".replies");
          
          const replyForm = document.createElement("div");
          replyForm.innerHTML = `
             <div class="comment-form" th:if="${member}!=null">
				<form th:action="@{'/'+article+'/'+reply}" id="commentForm" method="post">
					<input type="text" id="username" name="subject" style="width:58.9%" placeholder="제목" required>
					<input type="text" id="passwd" name="passwd" style="width:20%" placeholder="비밀번호" required>
					<input type="text" id="passwd" name="writer" style="width:20%; color:lightgray;" placeholder="등록자" required readonly th:value="${member.id}">
					<textarea id="commentText" name="content" placeholder="댓글 내용" required></textarea>
					<input class="btn btn-primary" type="submit" value="등록">
				</form>
			</div>
          `;
          repliesContainer.appendChild(replyForm);

          const replyFormElement = replyForm.querySelector(".reply-form");
          replyFormElement.addEventListener("submit", function(event) {
              event.preventDefault();
              
              const replyUsername = this.querySelector(".reply-username").value;
              const replyComment = this.querySelector(".reply-comment").value;
              
              const replyMessage = document.createElement("div");
              replyMessage.classList.add("reply");
              replyMessage.innerHTML = `
                  <div class="user">${replyUsername}</div>
                  <div class="message">${replyComment}</div>
              `;
              repliesContainer.appendChild(replyMessage);

              // 폼 제거
              repliesContainer.removeChild(replyForm);
          });
      });
  });
});

// script.js
// ...

document.addEventListener("DOMContentLoaded", function() {
  const replyButtons = document.querySelectorAll(".reply-btn");

  replyButtons.forEach(button => {
      button.addEventListener("click", function() {
          // ...
      });
  });

  const replyForms = document.querySelectorAll(".reply-form");

  replyForms.forEach(form => {
      form.addEventListener("submit", function(event) {
          event.preventDefault();
          
          const replyUsername = this.querySelector(".reply-username").value;
          const replyComment = this.querySelector(".reply-comment").value;
          
          const replyMessage = document.createElement("div");
          replyMessage.classList.add("reply");
          replyMessage.innerHTML = `
              <div class="user">${replyUsername}</div>
              <div class="message">${replyComment}</div>
          `;
          const parentComment = this.closest(".comment");
          const repliesContainer = parentComment.querySelector(".replies");
          repliesContainer.appendChild(replyMessage);

          // 폼 제거
          repliesContainer.removeChild(this);
      });
  });
});

// script.js
// ...

document.addEventListener("DOMContentLoaded", function() {
  const replyButtons = document.querySelectorAll(".reply-btn");

  replyButtons.forEach(button => {
      button.addEventListener("click", function() {
          // ...
      });
  });

  const replyForms = document.querySelectorAll(".reply-form");

  replyForms.forEach(form => {
      form.addEventListener("submit", function(event) {
          event.preventDefault();
          
          const replyUsername = this.querySelector(".reply-username").value;
          const replyComment = this.querySelector(".reply-comment").value;
          
          const replyMessage = document.createElement("div");
          replyMessage.classList.add("reply");
          replyMessage.innerHTML = `
              <div class="user">${replyUsername}</div>
              <div class="message">${replyComment}</div>
          `;

          const parentComment = this.closest(".comment");
          const repliesContainer = parentComment.querySelector(".replies");
          repliesContainer.appendChild(replyMessage);

          // 폼 제거
          repliesContainer.removeChild(this);

          // 대댓글 박스 추가
          const replyFormBox = document.createElement("div");
          replyFormBox.classList.add("reply-form-box");
          replyFormBox.innerHTML = this.outerHTML; // 폼의 HTML 복사
          repliesContainer.appendChild(replyFormBox);
      });
  });
});

// script.js
// ...

document.addEventListener("DOMContentLoaded", function() {
  const replyButtons = document.querySelectorAll(".reply-btn");

  replyButtons.forEach(button => {
      button.addEventListener("click", function() {
          // ...
      });
  });

  const replyForms = document.querySelectorAll(".reply-form");

  replyForms.forEach(form => {
      form.addEventListener("submit", function(event) {
          // ...
      });
  });

  const editButtons = document.querySelectorAll(".edit-btn");
  const deleteButtons = document.querySelectorAll(".delete-btn");

  editButtons.forEach(button => {
      button.addEventListener("click", function() {
          const commentMessage = this.parentElement.querySelector(".message");
          const originalMessage = commentMessage.textContent.trim();
          const editInput = document.createElement("input");
          editInput.type = "text";
          editInput.value = originalMessage;
          commentMessage.innerHTML = ""; // 기존 메시지 제거
          commentMessage.appendChild(editInput);

          const saveButton = document.createElement("button");
          saveButton.textContent = "저장";
          commentMessage.appendChild(saveButton);

          saveButton.addEventListener("click", function() {
              const newMessage = editInput.value;
              commentMessage.removeChild(editInput);
              commentMessage.removeChild(saveButton);
              commentMessage.textContent = newMessage;
          });
      });
  });

  deleteButtons.forEach(button => {
      button.addEventListener("click", function() {
          const comment = this.closest(".comment");
          comment.remove();
      });
  });
});
