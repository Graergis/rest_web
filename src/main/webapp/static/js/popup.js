﻿$(document).ready(function() { // вся магия после загрузки страницы
	$('a#go').click(function(event) { // ловим клик по ссылки с id="go"
		event.preventDefault(); // выключаем стандартную роль элемента
		$('#overlay').fadeIn(400, // сначала плавно показываем темную подложку
		function() { // после выполнения предъидущей анимации
			$('#modal_form').css('display', 'block') // убираем у модального
			// окна display: none;
			.animate({
				opacity : 1,
				top : '50%'
			}, 200); // плавно прибавляем прозрачность одновременно со
			// съезжанием вниз
		});
	});
	/* Закрытие модального окна, тут делаем то же самое но в обратном порядке */
	$('#modal_close, #overlay').click(function() { // ловим клик по крестику
		// или подложке
		$('#modal_form').animate({
			opacity : 0,
			top : '45%'
		}, 200, // плавно меняем прозрачность на 0 и одновременно двигаем окно
		// вверх
		function() { // после анимации
			$(this).css('display', 'none'); // делаем ему display: none;
			$('#overlay').fadeOut(400); // скрываем подложку
		});
	});
});
$(document).ready(function() { 
	$('a#go2').click(function(event) { 
		event.preventDefault(); 
		$('#overlay').fadeIn(400, 
		function() { 
			$('#modal_form2').css('display', 'block')
			.animate({
				opacity : 1,
				top : '50%'
			}, 200); 
		});
	});
	$('#modal_close2, #overlay').click(function() { 
		$('#modal_form2').animate({
			opacity : 0,
			top : '45%'
		}, 200, 
		function() { 
			$(this).css('display', 'none'); 
			$('#overlay').fadeOut(400); 
		});
	});
});
$(document).ready(function() {
	$('a#go3').click(function(event) { 
		event.preventDefault();
		$('#overlay').fadeIn(400, 
		function() { 
			$('#modal_form3').css('display', 'block')
			.animate({
				opacity : 1,
				top : '50%'
			}, 200); 
		});
	});
	$('#modal_close3, #overlay').click(function() { 
		$('#modal_form3').animate({
			opacity : 0,
			top : '45%'
		}, 200, 
		function() { 
			$(this).css('display', 'none');
			$('#overlay').fadeOut(400); 
		});
	});
});
$(function() {
	$('.dropdown-toggle').click(function() {
		$(this).next('.dropdown').toggle();
	});
	$(document).click(
			function(e) {
				var target = e.target;
				if (!$(target).is('.dropdown-toggle')
						&& !$(target).parents().is('.dropdown-toggle')) {
					$('.dropdown').hide();
				}
			});
});