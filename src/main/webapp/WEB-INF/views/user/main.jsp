<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>오늘의 가계부</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="shortcut icon" href="#">
    <link rel="stylesheet" type="text/css" media="screen" href="cssscreen.css">
    <link rel="stylesheet" type="text/css" media="print" href="cssprint.css">

    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>

    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #FFF5F8;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 90%;
            margin: auto;
            padding: 5%;
        }
        .header {
            text-align: center;
            font-size: 1.8em;
            font-weight: bold;
            color: #FF6B81;
            margin-bottom: 15px;
        }
        .form-group {
            margin-top: 12px;
            margin-bottom: 12px;
        }
        label {
            font-size: 1em;
            color: #FF6B81;
            display: block;
            margin-bottom: 5px;
        }
        input, select, button {
            width: 100%;
            padding: 10px;
            font-size: 1em;
            border-radius: 8px;
            border: 1px solid #FFCCD5;
            box-sizing: border-box;
        }
        input:focus, select:focus {
            outline: none;
            border-color: #FF6B81;
        }
        button {
            background-color: #FF6B81;
            color: white;
            border: none;
            margin-top: 10px;
            font-size: 1.1em;
            cursor: pointer;
        }
        button:hover {
            background-color: #FF8DA1;
        }
        .transaction-list {
            margin-top: 20px;
            background: #FFFFFF;
            border-radius: 10px;
            padding: 15px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }
        .transaction-item {
            display: flex;
            justify-content: space-between;
            padding: 8px 0;
            border-bottom: 1px solid #FFE0E6;
            font-size: 0.95em;
            align-items: center;
        }
        .transaction-item:last-child {
            border-bottom: none;
        }
        .expenditure-div {
            flex: 1;
        }
        .description {
            font-weight: bold;
            color: #333;
        }
        .category {
            color: #FF6B81;
            font-size: 0.85em;
            font-weight: bolder;
        }
        .amount {
            font-weight: bold;
            color: #333;
            text-align: right;
        }
        .total {
            font-weight: bold;
            font-size: 1.1em;
            color: #FF6B81;
            margin-top: 15px;
            text-align: center;
        }

        /* 팝업 스타일 */
        .popup {
            display: none; /* 초기에는 보이지 않도록 설정 */
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 300px;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ddd;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            z-index: 1000;
            text-align: center;
        }

        .popup button {
            margin-top: 15px;
            padding: 8px 16px;
            cursor: pointer;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
        }

        /* 팝업을 덮는 반투명 배경 */
        .popup-background {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 999;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="header">오늘의 소비 내역</div>

    <!-- 오늘의 거래 내역 -->
    <div class="transaction-list">
        <div class="transaction-item">
            <div class="expenditure-div">
                <span class="description">커피</span>
                <span class="category">식비</span>
            </div>
            <span class="amount">3,000 원</span>
        </div>
        <div class="transaction-item">
            <div class="expenditure-div">
                <span class="description">지하철</span>
                <span class="category">교통비</span>
            </div>
            <span class="amount">2,500 원</span>
        </div>
        <div class="transaction-item">
            <div class="expenditure-div">
                <span class="description">점심</span>
                <span class="category">식비</span>
            </div>
            <span class="amount">25,000 원</span>
        </div>
        <div class="total">⏬ 오늘의 총 소비: 30,500 원</div>
    </div>

    <!-- 소비 내역 입력 폼 -->
    <form id="save-transaction-form">
        <div class="form-group">
            <label for="amount">소비 금액 (원)</label>
            <input type="number" step="0.01" name="amount" id="amount" placeholder="금액 입력" required>
        </div>
        <div class="form-group">
            <label for="parent-category">카테고리</label>
            <select name="categoryInfo.parentCategoryId" id="parent-category" onchange="updateChildCategories()" required>
                <c:forEach var="parentCategory" items="${categories}">
                    <option value="${parentCategory.parentCategoryId}">${parentCategory.parentCategoryName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <select name="categoryInfo.childCategoryId" id="child-category" required>
                <c:forEach var="childCategory" items="${categories[0].childCategoryInfoList}">
                    <option value="${childCategory.categoryId}">${childCategory.categoryName}</option>
                </c:forEach>
            </select>
        </div>

            <script>
                const childCategoriesMap = {
                    <c:forEach var="parentCategory" items="${categories}">
                    "${parentCategory.parentCategoryId}": [
                        <c:forEach var="childCategory" items="${parentCategory.childCategoryInfoList}">
                        { "id": "${childCategory.categoryId}", "name": "${childCategory.categoryName}" }<c:if test="${!last}">,</c:if>
                        </c:forEach>
                    ],
                    </c:forEach>
                };

                function updateChildCategories() {
                    const parentSelect = document.getElementById("parent-category");
                    const childSelect = document.getElementById("child-category");

                    // 선택한 부모 카테고리의 ID
                    const selectedParentId = parentSelect.value;

                    // 자식 카테고리 초기화
                    childSelect.innerHTML = '';

                    if (selectedParentId && childCategoriesMap[selectedParentId]) {
                        const children = childCategoriesMap[selectedParentId];
                        children.forEach(child => {
                            const option = document.createElement("option");
                            option.value = child.id;
                            option.text = child.name;
                            childSelect.appendChild(option);
                        });
                    }
                }
            </script>

        <div class="form-group">
            <label for="description">메모</label>
            <input type="text" name="description" id="description" placeholder="메모 입력">
        </div>
        <button type="button" id="save-button">추가하기</button>
    </form>
</div>

<!-- 팝업 배경과 팝업 내용 -->
<div class="popup-background" id="popupBackground"></div>
<div class="popup" id="popup">
    <p>이것은 팝업 창입니다!</p>
    <button onclick="closePopup()">확인</button>
</div>

</body>
</html>

<script>
    document.getElementById('save-button').addEventListener('click', function () {
        let form = document.getElementById('save-transaction-form');

        $.ajax({
            url: "/user/save-transaction",
            type: "POST",
            data: $('#save-transaction-form').serialize(),
            success: function () {
                location.reload();
            },
            error: function (xhr, status, error) {
                openPopup()
            }
        })

    })

    // 팝업을 여는 함수
    function openPopup() {
        document.getElementById("popup").style.display = "block";
        document.getElementById("popupBackground").style.display = "block";
    }

    // 팝업을 닫는 함수
    function closePopup() {
        document.getElementById("popup").style.display = "none";
        document.getElementById("popupBackground").style.display = "none";
    }
</script>