//// Получение данных из модели Spring
//var managerCount = [[${managerCount}]];
//var restaurantCount = [[${restaurantCount}]];
//var menuCount = [[${menuCount}]];
//var foodCategoryCount = [[${foodCategoryCount}]];
//var foodCount = [[${foodCount}]];
//
//// Определение элемента canvas для гистограммы
//var chartCanvas = document.getElementById('chart');
//
//// Создание данных для гистограммы
//var chartData = {
//    labels: ['Менеджеры', 'Рестораны', 'Меню', 'Категории блюд', 'Блюда'],
//    datasets: [{
//        label: 'Количество записей',
//        backgroundColor: [
//            'rgba(255, 99, 132, 0.2)',
//            'rgba(54, 162, 235, 0.2)',
//            'rgba(255, 206, 86, 0.2)',
//            'rgba(75, 192, 192, 0.2)',
//            'rgba(153, 102, 255, 0.2)'
//        ],
//        borderColor: [
//            'rgba(255, 99, 132, 1)',
//            'rgba(54, 162, 235, 1)',
//            'rgba(255, 206, 86, 1)',
//            'rgba(75, 192, 192, 1)',
//            'rgba(153, 102, 255, 1)'
//        ],
//        borderWidth: 1,
//        data: [managerCount, restaurantCount, menuCount, foodCategoryCount, foodCount]
//    }]
//};
//
//// Создание объекта гистограммы с помощью Chart.js
//var chart = new Chart(chartCanvas, {
//    type: 'bar',
//    data: chartData
//});