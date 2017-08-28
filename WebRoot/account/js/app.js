/* 继承于 jquery 1.10.1.js 和 bootstrap 2.0.js 的前台框架 
 * 注意,所有元素为动态创建，所以，调用前要清除元素是否存在
 * 对于 Ajax 它无法被调用 ， 只能用它来调用别的函数
 * i t 为敏感词。使用时候注意级联函数是否也在调用
 * 对于上传到服务器的中文参数 一定要使用 encodeURI(encodeURI()) 二次编码
 * 代码书写要规范，Dreamweaver下不同级别用TAB缩进，使用严谨格式，不允许使用无声明元素，页面内不允许出现集散的javascript，使用外部插件声明版本及出处
 * 主要调用方法为 $( '元素标签' ).执行方法( {属性(key : value)} , ajax取值函数 , 自定义函数 )
 * 主要样式有 
 * 1. iCheck 		  复选、单选框
 * 2. iSelect		  下拉菜单
 * 3. iBuildtable	  表格
 * 4. view_pagination 分页（这里只针对table）
 * 5. iTreeselect	  树形菜单
   6. datetimepicker  时间选择器
 * 6. iPopup		  弹出窗口
 * 7. iFileupload     上传文件/导入表格
 * 8. iFlotchart      图表
 * 9. iMap 			  地图
 * 10.iScrollmenu	  滚动监听菜单（滑到可见的顶部）
 */


/* common App BEGIN */ 
var App = function () {
	
	/* handleSidebarMenu BEGIN */ /* 响应式导航 */
    var handleSidebarMenu = function () {
        jQuery('.page-sidebar').on('click', 'li > a', function (e) {
			if ($(this).next().hasClass('sub-menu') == false) {
				if ($('.btn-navbar').hasClass('collapsed') == false) {
					$('.btn-navbar').click();
				}
				return;
			}
			var parent = $(this).parent().parent();
			parent.children('li.open').children('a').children('.arrow').removeClass('open');
			parent.children('li.open').children('.sub-menu').slideUp(200);
			parent.children('li.open').removeClass('open');
			var sub = jQuery(this).next();
			if (sub.is(":visible")) {
				jQuery('.arrow', jQuery(this)).removeClass("open");
				jQuery(this).parent().removeClass("open");
				sub.slideUp(200);
			} else {
				jQuery('.arrow', jQuery(this)).addClass("open");
				jQuery(this).parent().addClass("open");
				sub.slideDown(200);
			}
			e.preventDefault();
		});
    }
	/* handleSidebarMenu END */

	
	/* handleGoTop BEGIN */ /* 返回顶部 */
    var handleGoTop = function () {
        jQuery('.footer').on('click', '.go-top', function (e) {
			App.scrollTo();
			e.preventDefault();
		});
    }
	/* handleGoTop END */
	
	
	/* headerSearch BEGIN */ /* 头部的搜索框 */
	var headerSearch=function(){
		var search_container=$('.header-search-container'),
			search_input=$('.header-search-input')
		search_container.click(function(){
			if(search_input.css('display')!='block'){
				search_container.addClass('display')
				search_input.focus().animate({'display':'block','width':'200px'},200)
			}
		})
		search_input.blur(function(){
			search_input.animate({'width':'0'},200,function(){
				search_container.removeClass('display')
			})
		})
	}
	/* headerSearch END */
	
	
	/* WindowAction BEGIN */ /* 弹窗效果(这里不用) */
	var WindowAction = function(){
		var _windows_1='<div class="slideshow"><div class="alert-window-elm"><div class="alert-window-elm-remove"><i class="icon-remove"></i></div></div></div>'
		$('.windows-actin').click(function(){
			$( '.slideshow' ).remove()
			$( '.page-content' ).append( _windows_1 )
			setTimeout( function(){$('.page-content').addClass('slideshow-open')},100)
			$('.alert-window-elm-remove').click(function(){
				$('.page-content').removeClass('slideshow-open')
				setTimeout(function(){$('.slideshow').remove()},200)
			})
		})
		var _windows_2='<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button><h3 id="myModalLabel">Modal header</h3></div><div class="modal-body"><p>One fine body…</p></div><div class="modal-footer"><button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button><button class="btn btn-primary">Save changes</button></div></div>'
		$('.windows-actin-2').click(function(){
			$('.page-content').append(_windows_2)
		})
	}
	/* WindowAction END */
	

    return {
		/* 调用App的初始化方法 */
        init: function () {
			headerSearch();
			WindowAction();
			handleGoTop();
            handleSidebarMenu();
        },
		/* 返回页面顶部的方法 */
        scrollTo: function (el, offeset) {
            pos = el ? el.offset().top : 0;
            jQuery('html,body').animate({
				scrollTop: pos + (offeset ? offeset : 0)
			}, 'slow');
        },
        scrollTop: function () {
            App.scrollTo();
        }
    };
}();

/* common app END */

(function($) {
	
	'use strict'
	
	App.init();
	
  
	/* icheckbox BEGIN */ /* 复选 单选框 */
	/* iCheck v1.0.2, http://git.io/arlzeA
	 * Powerful jQuery and Zepto plugin for checkboxes and radio buttons customization
	 * (c) 2013 Damir Sultanov, http://fronteed.com
	 * MIT Licensed
	 */
	var _iCheck = 'iCheck',
		_iCheckHelper = _iCheck + '-helper',
		_checkbox = 'checkbox',
		_radio = 'radio',
		_checked = 'checked',
		_unchecked = 'un' + _checked,
		_disabled = 'disabled',
		_determinate = 'determinate',
		_indeterminate = 'in' + _determinate,
		_update = 'update',
		_type = 'type',
		_click = 'click',
		_touch = 'touchbegin.i touchend.i',
		_add = 'addClass',
		_remove = 'removeClass',
		_callback = 'trigger',
		_label = 'label',
		_cursor = 'cursor',
		_mobile = /ipad|iphone|ipod|android|blackberry|windows phone|opera mini|silk/i.test( navigator.userAgent );
	$.fn[ _iCheck ] = function( options , fire ) {
		var handle = 'input[type="' + _checkbox + '"], input[type="' + _radio + '"]',
		  stack = $(),
		  walker = function( object ) {
			object.each( function() {
				var self = $( this );
				if ( self.is( handle ) ) {
					stack = stack.add( self );
				} else {
					stack = stack.add( self.find( handle ) );
				}
			});
		  };
		if ( /^(check|uncheck|toggle|indeterminate|determinate|disable|enable|update|destroy)$/i.test( options ) ) {
		options = options.toLowerCase();
		walker( this );
		return stack.each( function() {
			var self = $( this );
			if ( options == 'destroy' ) {
				tidy( self , 'ifDestroyed' );
			} else {
				operate( self , true , options );
			}
			if ( $.isFunction( fire ) ) {
				fire();
			}
		});
		} else if ( typeof options == 'object' || !options ) {
			var settings = $.extend({
					checkedClass : _checked,
					disabledClass : _disabled,
					indeterminateClass : _indeterminate,
					labelHover : true
				}, options ),
				selector = settings.handle,
				hoverClass = settings.hoverClass || 'hover',
				focusClass = settings.focusClass || 'focus',
				activeClass = settings.activeClass || 'active',
				labelHover = !!settings.labelHover,
				labelHoverClass = settings.labelHoverClass || 'hover',
				area = ( '' + settings.increaseArea ).replace( '%' , '' ) | 0;
			if ( selector == _checkbox || selector == _radio ) {
				handle = 'input[type="' + selector + '"]';
			}
			if ( area < -50 ) {
				area = -50;
			}
			walker( this );
			return stack.each( function() {
				var self = $( this );
				tidy( self );
				var node = this,
					id = node.id,
					offset = -area + '%',
					size = 100 + ( area * 2 ) + '%',
					layer = {
						position : 'absolute',
						top : offset,
						left : offset,
						display : 'block',
						width : size,
						height : size,
						margin : 0,
						padding : 0,
						background : '#fff',
						border : 0,
						opacity : 0
					},
					hide = _mobile ? {
						position : 'absolute',
						visibility : 'hidden'
					} : area ? layer : {
						position : 'absolute',
						opacity : 0
					},
					className = node[ _type ] == _checkbox ? settings.checkboxClass || 'i' + _checkbox : settings.radioClass || 'i' + _radio,
					label = $( _label + '[for="' + id + '"]').add( self.closest( _label ) ),
					aria = !!settings.aria,
					ariaID = _iCheck + '-' + Math.random().toString(36).substr(2,6),
					parent = '<div class="' + className + '" ' + ( aria ? 'role="' + node[ _type ] + '" ' : '' ),
					helper;
				if ( aria ) {
					label.each( function() {
						parent += 'aria-labelledby="';
						if ( this.id ) {
							parent += this.id;
						} else {
							this.id = ariaID;
							parent += ariaID;
						}
						parent += '"';
					});
				}
				parent = self.wrap( parent + '/>' )[ _callback ]( 'ifCreated' ).parent().append( settings.insert );
				helper = $( '<ins class="' + _iCheckHelper + '"/>' ).css( layer ).appendTo( parent );
				self.data( _iCheck , {o : settings, s : self.attr( 'style' ) }).css( hide );
				!!settings.inheritClass && parent[ _add ]( node.className || '' );
				!!settings.inheritID && id && parent.attr( 'id', _iCheck + '-' + id );
				parent.css( 'position' ) == 'static' && parent.css( 'position' , 'relative' );
				operate( self , true , _update );
				if ( label.length ) {
					label.on( _click + '.i mouseover.i mouseout.i ' + _touch , function( event ) {
						var type = event[ _type ],
							item = $( this );
						if ( !node[ _disabled ] ) {
							if ( type == _click ) {
								if ( $( event.target ).is( 'a' ) ) {
								  return;
								}
								operate( self , false , true );
							} else if ( labelHover ) {
								if ( /ut|nd/.test( type ) ) {
									parent[ _remove ]( hoverClass );
									item[ _remove ]( labelHoverClass );
								} else {
									parent[ _add ]( hoverClass );
									item[ _add ]( labelHoverClass );
								}
							}
							if ( _mobile ) {
								event.stopPropagation();
							} else {
								return false;
							}
						}
					});
				}
				self.on( _click + '.i focus.i blur.i keyup.i keydown.i keypress.i', function( event ) {
					var type = event[ _type ],
					key = event.keyCode;
					if ( type == _click ) {
						return false;
					} else if ( type == 'keydown' && key == 32 ) {
						if (!( node[ _type ] == _radio && node[ _checked ] ) ) {
							if ( node[ _checked ] ) {
								off( self , _checked );
							} else {
								on( self , _checked );
							}
						}
						return false;
					} else if ( type == 'keyup' && node[_type] == _radio ) {
						!node[ _checked ] && on( self , _checked );
					} else if ( /us|ur/.test( type ) ) {
						parent[ type == 'blur' ? _remove : _add ]( focusClass );
					}
				});
				helper.on( _click + ' mousedown mouseup mouseover mouseout ' + _touch , function( event ) {
					var type = event[ _type ],
						toggle = /wn|up/.test( type ) ? activeClass : hoverClass;
					if ( !node[ _disabled ] ) {
						if (type == _click) {
							operate( self , false , true );
						} else {
							if ( /wn|er|in/.test( type ) ) {
								parent[ _add ]( toggle );
							} else {
								parent[ _remove ]( toggle + ' ' + activeClass );
							}
							if ( label.length && labelHover && toggle == hoverClass ) {
								label[ /ut|nd/.test( type ) ? _remove : _add ]( labelHoverClass );
							}
						}
						if ( _mobile ) {
							event.stopPropagation();
						} else {
							return false;
						}
					}
				});
			});
		} else {
			return this;
		}
	};
	function operate( input, direct, method ) {
		var node = input[ 0 ],
		  	state = /er/.test( method ) ? _indeterminate : /bl/.test( method ) ? _disabled : _checked,
			active = method == _update ? {
				checked : node[ _checked ],
				disabled : node[_disabled ],
				indeterminate : input.attr( _indeterminate ) == 'true' || input.attr( _determinate ) == 'false'
			} : node[ state ];
		if ( /^(ch|di|in)/.test( method ) && !active ) {
		  	on( input, state );
		} else if ( /^(un|en|de)/.test( method ) && active ) {
		  	off( input, state );
		} else if ( method == _update ) {
			for ( var each in active ) {
				if ( active[ each ] ) {
				  	on( input , each , true );
				} else {
				  	off( input , each , true );
				}
			}
		} else if ( !direct || method == 'toggle' ) {
			if ( !direct ) {
				input[ _callback ]( 'ifClicked' );
			}
			if ( active ) {
				if ( node[ _type ] !== _radio ) {
				  	off( input , state );
				}
			} else {
				on( input , state );
			}
		}
	}
	function on( input , state , keep ) {
		var node = input[ 0 ],
			parent = input.parent(),
			checked = state == _checked,
			indeterminate = state == _indeterminate,
			disabled = state == _disabled,
			callback = indeterminate ? _determinate : checked ? _unchecked : 'enabled',
			regular = option( input , callback + capitalize( node[ _type ] ) ),
			specific = option( input , state + capitalize( node[ _type ] ) );
		if ( node[ state ] !== true ) {
			if ( !keep && state == _checked && node[ _type ] == _radio && node.name ) {
				var form = input.closest( 'form' ),
			  		inputs = 'input[name="' + node.name + '"]';
				inputs = form.length ? form.find( inputs ) : $( inputs );
				inputs.each( function() {
			  		if ( this !== node && $(this).data( _iCheck ) ) {
						off( $(this) , state );
			  		}
				});
			}
			if ( indeterminate ) {
				node[ state ] = true;
				if ( node[ _checked ] ) {
					off( input , _checked , 'force' );
				}
			} else {
				if ( !keep ) {
				  	node[ state ] = true;
				}
				if ( checked && node[ _indeterminate ] ) {
				  	off( input , _indeterminate , false );
				}
			}
		  	callbacks( input , checked , state , keep );
		}
		if ( node[ _disabled ] && !!option( input , _cursor , true ) ) {
		  	parent.find( '.' + _iCheckHelper ).css( _cursor , 'default' );
		}
		parent[ _add ]( specific || option( input , state ) || '' );
		if ( !!parent.attr( 'role' ) && !indeterminate ) {
		  	parent.attr( 'aria-' + ( disabled ? _disabled : _checked ), 'true' );
		}
		parent[ _remove ]( regular || option( input, callback ) || '' );
	}
	function off( input , state , keep ) {
		var node = input[ 0 ],
			parent = input.parent(),
			checked = state == _checked,
			indeterminate = state == _indeterminate,
			disabled = state == _disabled,
			callback = indeterminate ? _determinate : checked ? _unchecked : 'enabled',
			regular = option( input , callback + capitalize( node[ _type ] )),
			specific = option( input, state + capitalize( node[ _type ] ) );
		if ( node[ state ] !== false ) {
			if ( indeterminate || !keep || keep == 'force' ) {
				node[ state ] = false;
			}
			callbacks( input , checked , callback , keep );
		}
		if ( !node[ _disabled ] && !!option( input , _cursor , true ) ) {
		  	parent.find( '.' + _iCheckHelper ).css( _cursor , 'pointer' );
		}
		parent[ _remove ]( specific || option( input , state ) || '' );
		if ( !!parent.attr( 'role' ) && !indeterminate ) {
		  	parent.attr( 'aria-' + ( disabled ? _disabled : _checked ) , 'false' );
		}
		parent[ _add ]( regular || option( input, callback ) || '' );
	}
	function tidy( input , callback ) {
		if ( input.data( _iCheck ) ) {
		  	input.parent().html( input.attr( 'style' , input.data( _iCheck ).s || '' ) );
		  	if ( callback ) {
				input[ _callback ]( callback );
		 	}
		  	input.off( '.i' ).unwrap();
		 	$( _label + '[for="' + input[ 0 ].id + '"]' ).add( input.closest( _label ) ).off( '.i' );
		}
	}
	function option( input , state , regular ) {
		if ( input.data( _iCheck ) ) {
		  	return input.data( _iCheck ).o[ state + ( regular ? '' : 'Class' ) ];
		}
	}
	function capitalize( string ) {
		return string.charAt( 0 ).toUpperCase() + string.slice( 1 );
	}
	function callbacks( input , checked , callback , keep ) {
		if ( !keep ) {
			if ( checked ) {
				input[ _callback ]( 'ifToggled' );
			}
			input[ _callback ]( 'ifChanged' )[ _callback ]( 'if' + capitalize( callback ) );
		}
	}
	/* icheckbox END */
  
    /* 发送异步请求的句柄参数 */
	var xmlhttp = null;
	if(window.ActiveXObject){
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP")
	}else if(window.XMLHttpRequest){
		xmlhttp=new XMLHttpRequest()
	}
  
  
	/* iSelect BEGIN */ /* 下拉菜单 */ /* 这里注意智能筛选和复选框可以交互使用 但是智能 筛选 和 复选框 不能和含有 表格 的 下拉菜单 交互使用，如果出现将失去作用，造成代码运行缓慢 */
	/* 参数 ：
	 * 1.是否无样式		isnormal
	 * 2.自动筛选			intelligent
	 * 3.复选框			checkbox
	 * 4.下拉表格			filterandcheck
	 * 5.请求路径			ajaxURL
	 * 6.发送后台的参数	jsonstr
	 * 7.参数名			jsonname
	 * 8.自定义函数接口	freestyle
	 */
	var _iSelect = 'iSelect',
		clearspace = /\s/g;
	$.fn[ _iSelect ] = function( options , freestyle ) {
		var handle = 'dropdown',
			stack = $() ,
			/* 这里用来判断传来的参数是否为javascript函数 如果是，则置入jquery对象 */
			worker = function( object ) {
				object.each( function () {
					var self = $( this );
					/*if ( self.hasClass( handle )) {*/
						stack = stack.add( self );
					/*}*/
				})
			};
		if ( typeof options == 'object' || !options ) {
			var settings = $.extend({
				isnormal : false,		/* 是否为正常，正常状态下后几项属性都是false */
				intelligent : false,	/* 智能筛选 */
				checkbox : false,		/* 是否含有复选 */
				filterandcheck : false,	/* 含有表格的查询 */
				ajaxURL : '',			/* 请求路径 */
				jsonstr : '',			/* 发送后台的参数 */
				jsonname : ''			/* 参数名 */
				}, options ),
				_isnormal = settings.isnormal,
				_intelligent = settings.intelligent,
				_checkbox = settings.checkbox,
				_filterandcheck = settings.filterandcheck,
				_ajaxURL = settings.ajaxURL,
				_jsonstr = settings.jsonstr,
				_jsonname = settings.jsonname;
			worker( this );
			return stack.each( function () {
				var self = $( this );
				/* normal 下拉菜单为不带任何特殊功能的下拉菜单,这里暂时缺省 */
				/*if(  _isnormal == false  ) {*/
					self.on( 'click' , function () {
						/* 必须有此判断，否则下拉表格将无法使用 */
						if ( _filterandcheck && self.find( '.dropdown-menu' ).length > 0 ) {
							self.find( '.dropdown-menu' ).addClass( 'must-play' );
							return;
						}
						self.find( '.dropdown-menu' ).remove();
						/* 创建哪种模式的下拉菜单 */
						var kr = 0;
						if ( _checkbox ) kr = 1;
						if ( _filterandcheck ) kr = 2;
						/* 调用方法动态创建下拉菜单 */
						iselectajax( _ajaxURL , _jsonstr , _jsonname , kr , self );
						
						if ( self.children().eq( 1 ).children().length > 6 ) {
							self.children().eq( 1 ).css( { 'height' : '180px' , 'overflow-y' : 'scroll' } )
						}
						$( this ).children().eq( 0 ).children().eq( 0 ).focus();
						if ( _intelligent ) {
							self.children().eq( 0 ).children().eq( 0 ).on( 'input propertychange' , function() {
								var _str = $(this).val().replace( clearspace , '' ),
									_elem = self.children().eq( 1 ).children();
								for (var c = 0 ; c < _elem.length ; c++) {
									if ( _elem.eq( c ).children().text().replace( clearspace , '' ).indexOf( _str ) < 0 && _str.length > 0 ){
										if ( _checkbox && c < _elem.length - 2 ) {
											_elem.eq( c ).css( 'display' , 'none' );
										} else if ( _checkbox == false && c < _elem.length ) {
											_elem.eq( c ).css( 'display' , 'none' );
										} else {
											_elem.eq( c ).css( 'display' , 'block' );
										}
									} else {
										_elem.eq( c ).css( 'display' , 'block' );
									}
								}
							});
						}
						if ( _checkbox ) {
							var _elem = self.children().eq( 1 ).children(),
								_elem_checkbox = self.children().eq( 1 ).children().eq( _elem.length - 1 );
							_elem_checkbox.children().eq( 0 ).children().eq( 0 ).children().eq( 1 ).on( 'click' , function () {
								if ( $( this ).parent().hasClass( 'checked' ) ) {
									for ( var c = 0 ; c < _elem.length ; c++ ) {
										if ( c < _elem.length - 2 ) {
											if ( _elem.eq( c ).css( 'display' ) == 'block' ){
												_elem.eq( c ).children().eq( 0 ).children().eq( 0 ).addClass( 'checked' );
											}
										}
									}
								} else {
									for ( var c = 0 ; c < _elem.length ; c++) {
										if ( c < _elem.length - 2 ) {
											_elem.eq( c ).children().eq( 0 ).children().eq( 0 ).removeClass( 'checked' );
										}
									}
								}
							});
						}
						self.find( '.dropdown-menu > li > a' ).on( 'click' , function(){
							if ( ( _intelligent || _checkbox ) && _filterandcheck == false )
								var _ksr = $( this ).text();
								$( this ).parent().parent().parent().children().eq( 0 ).children().eq( 0 ).attr( 'value' , _ksr );
						});
						self.find( '.dropdown-menu > li > a  .iCheck-helper' ).on( 'click' , function(){
							var _ksr = $( this ).parent().parent().text(),
								_kar = $( this ).parent().parent().parent().parent().parent().children().eq( 0 ).children().eq( 0 ).val(),
								_kvr ;
							if ( $( this ).parent().parent().parent().parent().find( '.checked' ).length > 1 ){
								_kvr = _kar + ',' + _ksr; 
							} else {
								_kvr = _ksr;
							}
							$( this ).parent().parent().parent().parent().parent().children().eq( 0 ).children().eq( 0 ).attr( 'value' , _kvr );
						});
						/* 此处为智能下拉表格 因为其中的操作和普通表格相似，所以这个功能由表格框架完成，这个暂定为接口可自定义函数 */
						if ( _filterandcheck ) { }
					});	
				/*}*/
				/* 自定义接口函数 */
				if ($.isFunction(freestyle)) {
					freestyle();
				}
			})
		}
	};
	function iselectajax ( _ajaxURL , _jsonstr , _jsonname , kr , _obj ) {
		/* 此处使用ajax发送请求 接口函暂时不使用 */
		/*xmlhttp.open( 'post' , _ajaxURL , true );
		xmlhttp.setRequestHeader( 'Content-Type' , 'application/x-www-form-urlencoded' );
		xmlhttp.send( _jsonname + '=' + _jsonstr );
		xmlhttp.onreadystatechange = function(){
			if ( xmlhttp.readyState == 4 && xmlhttp.status == 200 ) {
				var result = eval( xmlhttp.responseText );*/
				$( '.dropdown .dropdown-menu' ).remove();
				/* 自动筛选 */
				if ( kr == 0 ) {
					/*******/
					var arr = ['测试1','测试2','测试3','测试3','测试3','测试4','测试4','测试5','测试6'],
					/*******/
						acc = new Array();
					for ( var i = 0 ; i < arr.length ; i++ ) {
						acc[ i ] = '<li role="presentation"><a role="menuitem">' + arr[ i ] + '</a></li>';
					}
					_obj.append('<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">' + acc.join( '' ) + '</ul>');
				} 
				/* 带复选 */
				if ( kr == 1 ) {
					/*******/
					var arr = ['测试1','测试2','测试3','测试3','测试3','测试4','测试4','测试5','测试6'];
					/*******/
					var	acc = new Array();
					for ( var i = 0 ; i < arr.length ; i++ ) {
						acc[ i ] = '<li role="presentation"><a role="menuitem"><input  type="checkbox" >' + arr[ i ] + '</a></li>';
					}
					acc[ acc.length ]='<li class="divider"></li><li role="presentation"><a role="menuitem"><input  type="checkbox" >全选</a></li>'
					_obj.append('<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">' + acc.join( '' ) + '</ul>');
					$( 'input' ).iCheck();
				}
				/* 带 表格 */
				if ( kr == 2 ) {
					/* table 永远把每一行的id值放在首位并且隐藏 然后是序号，然后是复选框 剩下的顺序根据数据库存储的值定义 工具列永远放在末列 */
					var _div_container = '<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1"><li></li></ul>';
					/* <table class="table table_select_checkbox"></table> */
					_obj.append( _div_container );
					_obj.find( '.dropdown-menu > li' ).iBuildtable({
						table_checkbox : true,
					    isinsert : false,
					    isdelete : false,
					    isupdate : false,
					    issearch : true,
					    isthsort : false,
					    dragth : true,
					    isinsidetdsort : true,
					    pagination : true,
					    isdoubleclick : false,
					    isrolenumber : 3,
					    isdiscription : false,
					    isrefresh : false,
					    isremove : true,
						ajaxURL : _ajaxURL,
						jsonstr : _jsonstr,
						jsonname : _jsonname
				    });
				}
			/*}
		}*/
	}
	/* iSelect END */
	
	
	
	/* iBuildtable BRGIN */ /* 表格 */
	/* 说明下，这个表格并非是和以前一样去后台取数然后创建，而是取出所在页的所有数据然后通过隐藏莫一列或拖动某一列达到要求，这个很有难度 */
	/* 参数 ：
	 * 1.是否包含复选框	table_checkbox
	 * 2.添加按钮			isinsert
	 * 3.删除按钮			isdelete
	 * 4.更新按钮			isupdate
	 * 5.查询按钮			issearch
	 * 6.列排序和隐藏按钮	isthsort
	 * 7.是否将隐藏的列名缓存住		issave
	 * 8.请求路径			ajaxURL 这里的路径参数最好是一种递增方式的逻辑路径段，这样可以保证以后的所有的向后请求无需重写，否则就要在参数中定义更多的路径参数，增加了编写难度
	 * 9.发送后台的参数	jsonstr
	 * 10.参数名			jsonname
	 * 11.是否包含拖拽功能 dragth
	 * 12.是否包含对列内信息排序功能	isinsidetdsort
	 * 13.分页功能			pagination
	 * 14.双击事件			isdoubleclick
	 * 15.每页显示条数		isrolenumber 向后台发送请求时需要使用，创建tbody时也需要使用
	 * 16.表格工具列			isdiscription
	 * 17 表格内部刷新		isrefresh
	 * 18.移除表格功能		isremove
	 * 19.添加功能接口		fn_insert
	 * 20.删除功能接口		fn_delete
	 * 21.修改功能接口		fn_update
	 * 22.查询功能接口		fn_search
	 * 23.排序列和隐藏列接口	fn_tablesort
	 * 24.对列内信息排序功能接口	fn_isinsidetdsort
	 * 25.分页功能接口		fn_pagination
	 * 26.双击事件接口		fn_isdoubleclick
	 * 27.每页显示条数功能接口	fn_isrolenumber
	 * 28.表格工具列功能接口	fn_isdiscription
	 * 29.自定义函数接口		freestyle
	 */
	var _iBuildtable = 'iBuildtable';
	$.fn[ _iBuildtable ] = function ( options , fn_options , freestyle ) {
		var handle = 'table',
			stack = $(),
			worker = function ( object ) {
				object.each( function () {
					var self = $( this );
					/*if ( self.hasClass( handle ) ) {*/
						stack = stack.add( self );
					/*}*/
				})
			};
		if ( typeof options == 'object' || options ) {
			var settings = $.extend({
					table_checkbox : false,	/* 是否存在复选框 */
					isinsert : false,		/* 添加 */
					isdelete : false,		/* 删除 */
					isupdate : false,		/* 修改 */
					issearch : false,		/* 查询 */
					isthsort : false,		/* 把列排序 and 隐藏显示列 */
					dragth : false,			/* 拖拽列 列的宽度 */
					isinsidetdsort : false,	/* 对列内信息排序 */
					pagination : false,		/* 分页 */
					isdoubleclick : false,	/* 双击事件 */
					isrolenumber : 30,		/* 一页显示条数 */
					isdiscription : false,	/* 是否存在工具列 */
					isrefresh : false,		/* 刷新 */
					isremove : false,		/* 是否存在移除 table 的功能 */
					issave : false,			/* 是否将隐藏的列名缓存住 */
					ajaxURL : '',			/* 请求路径 */
					jsonstr : '',			/* 发送后台的参数 */ /* 最后一个参数为查询条数 */
					jsonname : ''			/* 参数名 */
				} , options ),
				_table_checkbox = settings.table_checkbox,
				_isinsert = settings.isinsert,
				_isdelete = settings.isdelete,
				_isupdate = settings.isupdate,
				_issearch = settings.issearch,
				_isthsort = settings.isthsort,
				_dragth = settings.dragth,
				_isinsidetdsort = settings.isinsidetdsort,
				_pagination = settings.pagination,
				_isdoubleclick = settings.isdoubleclick,
				_isrolenumber = settings.isrolenumber,
				_isdiscription = settings.isdiscription,
				_isrefresh = settings.isrefresh,
				_isremove = settings.isremove,
				_issave = settings.issave,
				_ajaxURL = settings.ajaxURL,
				_jsonstr = settings.jsonstr,
				_jsonname = settings.jsonname,
				fn_settings = $.extend({
					fn_insert : '',
					fn_delete : '',
					fn_update : '',
					fn_search : '',
					fn_tablesort : '',
					fn_isinsidetdsort : '',
					fn_pagination : '',
					fn_isdoubleclick : '',
					fn_isrolenumber : '',
					fn_isdiscription : ''
				} , fn_options ),
				_fn_insert = fn_settings.fn_insert,
				_fn_delete = fn_settings.fn_delete,
				_fn_update = fn_settings.fn_update,
				_fn_search = fn_settings.fn_search,
				_fn_tablesort = fn_settings.fn_tablesort,
				_fn_isinsidetdsort = fn_settings.fn_isinsidetdsort,
				_fn_pagination = fn_settings.fn_pagination,
				_fn_isdoubleclick = fn_settings.fn_isdoubleclick,
				_fn_isrolenumber = fn_settings.fn_isrolenumber,
				_fn_isdiscription = fn_settings.fn_isdiscription;
			worker( this );
			return stack.each( function () {
				/* 创建table容器 */
				$( this ).append( '<div class="table_container"><table class="table table_fun"></table></div>' );
				var self = $( this ).find( '.table.table_fun' ),
					checktableth = '',		/* 复选框表头列名 */
					checktabletd = '',		/* 列内的复选框 */
					table_button_arr = new Array(),			/* table 顶部操作按钮数组容器 */
					table_execute_topcontainer = '<div class="table_execute_topcontainer">',   		/* table 头部容器放置 增 删 改 查 隐藏显示或排序 操作 */
					table_execute_bottomcontainer = '',		/* table 底部容器放置 分页的一系列操作 */
					tabletools_columnth = '',	/* 表头的工具列名 */
					tabletools_columntd = '';	/* 末列的工具元素 */
				
				/* 此处使用ajax发送请求 函数暂时不使用 */
				//_jsonstr[ _jsonstr.length -1 ] = _isrolenumber;
				/*xmlhttp.open( 'post' , _ajaxURL , true );
				xmlhttp.setRequestHeader( 'Content-Type' , 'application/x-www-form-urlencoded' );
				xmlhttp.send( _jsonname + '=' + _jsonstr );
				xmlhttp.onreadystatechange = function(){
					if ( xmlhttp.readyState == 4 && xmlhttp.status == 200 ) {
						var result = eval( xmlhttp.responseText );*/
						/* 普通表格参数 需要从后台取出 【 表头 ， 内容 ， 共多少列 ， 共多少行】 */
						/* 用一个10列 100行 具备所有功能的table测试 */ 
						/****************/
						var table_arr = new Array();
						if ( $( this ).hasClass( 'tooltip-demo_table' ) ) {
							var table_arr_1 = new Array(),
							table_arr_2 = new Array(),
							table_arr_3 = new Array();
							for ( var i = 0 ; i < _isrolenumber ; i++ ) {
								for ( var c = 0 ; c < 10 ; c++  ) {
									if ( c == 0 ) {
										table_arr_1[ i*10 ] = i;
									} else {
										table_arr_1[ i*10 + c ] = '测试' + i;
									}
								}
							}
							/* 从后台取出的数据中一定要存储共多少列，这样才能创建出表格，上边我以10列定义 */
							table_arr_2 = ['10','100'];
							table_arr_3 = ['ID','测试1','测试2','测试3','测试4','测试5','测试6','测试7','测试8','测试9'];
							var table_arr_4 = table_arr_3.concat( table_arr_1 );
							table_arr = table_arr_4.concat( table_arr_2 );
						} else {
							/* 下拉菜单中的表格测试 */
							table_arr=['ID','测试1','测试2','测试3','测试4','1','试试','shishi','shishi','shishi','2','shishi','shishi','shishi','shishi','3','shishi','shishi','shishi','shishi','5','100'];
						}
						/****************/
						var separate_list = table_separate_list( table_arr ), /* 参数赋值 */
							table_column = separate_list._table_column,		/* table 共几列 除了必显列外由数据库中读出的数据列 */
							table_parameter_th = separate_list._table_parameter_th,			/* table 表头 */
							table_parameter_td = separate_list._table_parameter_td,	/* table td 内容 */
							table_parameter_allcount = separate_list._table_parameter_allcount;	/* 数据库中共有多少行被查出的信息 */
						if ( _table_checkbox ) {
							checktableth = '<th class="table_checkbox">全选</th>';
							checktabletd = '<td class="table_checkbox"><input class="table-checkbox" type="checkbox" /></td>';
						}
						if ( _isinsert ) {
							table_button_arr[ table_button_arr.length ] = '<button type="button" class="btn btn-info table_insert"><i class="icon-plus"></i></button>';
						}
						if ( _isdelete ) {
							table_button_arr[ table_button_arr.length ] = '<button type="button" class="btn btn-info table_delete"><i class="icon-trash"></i></button>';
						}
						if ( _isupdate ) {
							table_button_arr[ table_button_arr.length ] = '<button type="button" class="btn btn-info table_update"><i class="icon-edit"></i></button>';
						}
						if ( _isthsort ) {
							table_button_arr[ table_button_arr.length ] = '<button type="button" class="btn btn-info table_tablesort"><i class="icon-th-large"></i></button>';
						}
						if ( _isrefresh ) {
							table_button_arr[ table_button_arr.length ] = '<button type="button" class="btn btn-info table_refresh"><i class="icon-refresh"></i></button>';
						}
						if ( _issearch ) {
							table_button_arr[ table_button_arr.length ] = '<button type="button" class="btn btn-info table_search"><i class="icon-search"></i></button>';
						}
						if ( _isdiscription ) {
							tabletools_columnth = '<th class="table-tools">工具</th>';	/* 可以用图标代替 */
							tabletools_columntd = '<td class="table-tools"><span>详细</span><span>修改</span><span>删除</span></td>';		/* 这里先写中文之后用图标代替 */
						}
						if ( _isremove ) {
							table_button_arr[ table_button_arr.length ] = '<button type="button" class="btn btn-info dropdown_table_remove"><i class="icon-remove"></i></button></div>'
						}
						if ( table_button_arr.length > 0 ) {
							self.before( table_execute_topcontainer + table_button_arr.join( '' ) );
						}
						if ( _pagination ) {
							view_pagination( table_parameter_allcount , _isrolenumber , self , _ajaxURL , _jsonstr , _jsonname , checktabletd , tabletools_columntd );
						}
						var table_role = table_parameter_allcount > _isrolenumber ? _isrolenumber : table_parameter_allcount;
						/* 开始创建表格 */
						build_table(  table_column , table_parameter_th , checktableth , tabletools_columnth , table_role , table_parameter_td , checktabletd , tabletools_columntd , self );
						/* 表格中的复选框 全选 操作 这里有个小bug 因为 其它表格已经把触发元素指向外部 ，这里无法重新取回 ， 需要把触发对象重新定向一次 */
						//var checkbox_list = self.find( '.table_checkbox' );
						$( document ).click( function( e ){
							var elem_doc = $(e.target).attr('class');
							var checkbox_list =  $(e.target).parent().parent().parent().find( '.table_checkbox' );
							if ( elem_doc == 'table_checkbox' ) {
									if ( checkbox_list.eq( 0 ).hasClass( 'checked' ) ) {
										for ( var c = 0 ; c < checkbox_list.length ; c++ ) {
											if ( c == 0 ) {
												checkbox_list.eq( 0 ).removeClass( 'checked' );
											} else {
												checkbox_list.eq( c ).children().eq( 0 ).removeClass( 'checked' );
											}
										}
									} else {
										for ( var c = 0 ; c < checkbox_list.length ; c++ ) {
											if ( c == 0 ) {
												checkbox_list.eq( 0 ).addClass( 'checked' );
											} else {
												checkbox_list.eq( c ).children().eq( 0 ).addClass( 'checked' );
											}
										}
									}
							} else if ( elem_doc == 'table_checkbox checked' ) {
									if ( checkbox_list.eq( 0 ).hasClass( 'checked' ) ) {
										for ( var c = 0 ; c < checkbox_list.length ; c++ ) {
											if ( c == 0 ) {
												checkbox_list.eq( 0 ).removeClass( 'checked' );
											} else {
												checkbox_list.eq( c ).children().eq( 0 ).removeClass( 'checked' );
											}
										}
									} else {
										for ( var c = 0 ; c < checkbox_list.length ; c++ ) {
											if ( c == 0 ) {
												checkbox_list.eq( 0 ).addClass( 'checked' );
											} else {
												checkbox_list.eq( c ).children().eq( 0 ).addClass( 'checked' );
											}
										}
									}
							}
							preventdefault
						});
						$( 'input' ).iCheck();	/* 为新创建的复选框添加样式 */
						/* 表格中的 添加 操作 */
						self.parent().find( '.table_insert' ).on( 'click' , function () { });
						/* 表格中的 删除 操作 */
						self.parent().find( '.table_delete' ).on( 'click' , function () { });
						/* 表格中的 修改 操作 */
						self.parent().find( '.table_update' ).on( 'click' , function () { });
						/* 表格中的 查询 操作 */
						self.parent().find( '.table_search' ).on( 'click' , function () {
							if ( self.parent().find( '.table_search_input' ).length > 0 ) {
								var _text = self.parent().find( '.table_search_input' ).val();
								if ( _text.replace( clearspace , '' ).length > 0 ) {
									tablesearchajax( self , _isrolenumber , table_parameter_td , checktabletd , tabletools_columntd , _ajaxURL , _jsonstr , _jsonname );
								}
							} else {
								$( this ).parent().append( '<input type="text" class="table_search_input" />' );
								self.parent().find( '.table_search_input' ).focus();
							}
						});
						/* 表格中的 排序和隐藏列 操作 被隐藏的列名放置于cookie中 */
						self.parent().find( '.table_tablesort' ).on( 'click' , function () {
							if ( $( this ).parent().find( '.column_iselect_container' ).length > 0 ){
								$( this ).parent().find( '.column_iselect_container' ).remove();
							}else{
								var _self = $( this ),
									acc = new Array(),
									_tb_th = _self.parent().parent().find( '.table th' ),
									/* 弹出层变更为下拉菜单 */
									/*_container = '<div class="slideshow"><div class="column_hide_container"><div class="column_container_header"><span class="column_spantitle">显示或隐藏列</span><span class="column_spanremove"><i class="icon-remove"></i></span></div><div class="column_container_body"><ul class="column_table_th"></ul><ul class="column_sort_th"></ul></div><div class="column_container_footer"><span class="column_selectallbtn"></span><span class=""></span></div></div></div>'*/
									/* 下拉菜单显示可显列 */
									_container = '<div class="column_iselect_container"><ul class="column_table_th"></ul></div>';
								for ( var c = 0; c < _tb_th.length - 3 ; c++ ) {
									acc[ c ] = '<li><input type="checkbox" class="column_th_check" />' + _tb_th.eq( c + 3 ).text() + '</li>';
								}
								/* 加载数据 */
								_self.parent().parent().find( '.table_execute_topcontainer' ).append( _container );
								$( '.column_table_th' ).append( acc.join( '' ) );
								$( '.column_th_check' ).iCheck();
								/* 如果是可见的复选框自动选中 , 并且显示列的排列顺序 */
								var aee = new Array(),
									_table_visableth = _self.parent().parent().children().eq( 1 ).children().eq(0).children().children();
								for ( var c = 0 ; c < _table_visableth.length ; c++ ) {
									for ( var j = 0 ; j < _table_visableth.length ; j++ ) {
										if ( $( '.column_table_th > li' ).eq( j ).text() == _table_visableth.eq( c ).text() && _table_visableth.eq( c ).is( ':visible' ) == true ) {
											$( '.column_table_th > li' ).eq( j ).children().eq( 0 ).addClass( 'checked' );
											aee[ c ] = '<li><input type="checkbox" class="column_sort_th_checked" />' + $( '.column_table_th > li' ).eq( j ).text() + '<li>';
										}
									}
								}
								/* 点击li自动选中 */
								$( '.column_table_th > li' ).on( 'click' , function(){
									if ( $( this ).children().eq( 0 ).hasClass( 'checked' ) ) {
										$( this ).children().eq( 0 ).removeClass( 'checked' );
									} else {
										$( this ).children().eq( 0 ).addClass( 'checked' );
									}
								});
								/* 对于下拉菜单隐藏列 */
								 _self.parent().find( '.column_table_th > li' ).on( 'click' , function(){
									var kar = $( this ).text(),
										_table_th = self.find( 'th' );
									for ( var c = 0 ; c < _table_th.length ; c++ ) {
										if ( _table_th.eq( c ).text() == kar ) {
											var _cla = _table_th.eq( c ).prop( 'className' );
											if( self.find( '.' + _cla ).is( ':visible' ) == false ) {
												self.find( '.' + _cla ).show();
												if ( _issave ) {
													issavecookie( '.' + _cla , 1 );
												}
											} else {
												self.find( '.' + _cla ).hide();
												if ( _issave ) {
													issavecookie( '.' + _cla , 0 );
												}
											}
										}
									}
								});
								_self.parent().find( '.column_table_th > li > .icheckbox > .iCheck-helper' ).on( 'click' , function(){
									var kar = $( this ).parent().parent().text(),
										_table_th = self.find( 'th' );
									for ( var c = 0 ; c < _table_th.length ; c++ ) {
										if ( _table_th.eq( c ).text() == kar ) {
											var _cla = _table_th.eq( c ).prop( 'className' );
											if( self.find( '.' + _cla ).is( ':visible' ) == false ) {
												self.find( '.' + _cla ).show();
												$( this ).parent().addClass('checked')
												if ( _issave ) {
													issavecookie( '.' + _cla , 1 );
												}
											} else {
												self.find( '.' + _cla ).hide();
												$( this ).parent().removeClass('checked')
												if ( _issave ) {
													issavecookie( '.' + _cla , 0 );
												}
											}
										}
									}
								});
								/* 对于弹出层 */
								/*var _otimer = setTimeout( function(){
									$( '.page-content' ).addClass( 'slideshow-open' );
									clearInterval( _otimer );
								} , 150 );
								
								$( '.column_spanremove' ).on( 'click' , function(){
									$( '.page-content' ).removeClass( 'slideshow-open' );
									var _ootimer = setTimeout( function(){
										$( '.slideshow' ).remove();
										clearInterval( _ootimer );
									} , 200 ); 
								});*/
							}
						});
						if ( _issave ) {
							hide_column( self );
						}
						/* 表格中的 列内信息排序 操作 */
						if ( _isinsidetdsort ) {
							
						}
						/* 表格中的 分页组合 操作 */
						
						/* 表格中的 双击 操作 */
						if ( _isdoubleclick ) {
							
						}
						/* 表格刷新 */
						self.parent().find( '.table_refresh' ).on( 'click' ,function () {
							
						});
						/* 移除 下拉表格 操作(此处针对下拉菜单中的表格) */
						self.parent().find( '.dropdown_table_remove' ).on( 'click' , function () {
							$( this ).parent().parent().parent().parent().remove();
						});
						/* 改变列宽 */
						if ( _dragth ) {
							var _linemove = false,
								_line = '<div class="drag_line" style="width:1px;background:#f00;position:absolute;display:none;z-index:999999;"></div>',
								_tableth = $('.table > thead  th'),
								_currth = null,
								_thself = null;
							_tableth.on( 'mousedown' , function( e ){
								_thself = $( this );
								var _self = $( this ),
									_pos = _self.offset(),
									_thleft = _pos.left,
									_thtop = _pos.top,
									_thwidth = _self.width(),
									_left = _self.position().left ;
								if ( _self.prevAll().length < 3 || _self.nextAll().length < 1 ){
									return
								}
								e = window.event || e;
								var mouseX = e.pageX;
								if ( mouseX > _thleft + _thwidth - 5  ) {
									var _tbcontainer = _self.parent().parent().parent().parent();
									_tbcontainer.append( _line );
									$( '.drag_line' ).css({ 'display' : 'block' , 'height' : _tbcontainer.height() , 'top' : '0' , 'left' : mouseX - _tbcontainer.offset().left });
									_linemove = true;
									_currth = _self.prop( 'className' );
								}
							});
							$(document).on( 'mousemove' , function( e ){
								e = window.event || e;
								if ( _linemove ) {
									var _mouseX = e.pageX,
										_tbcontainerleft = _mouseX - _thself.parent().parent().parent().parent().offset().left,
										_tbthwidth = _mouseX - _thself.offset().left;
									$('.page-container').addClass( 'stopto_select' );
									$( '.drag_line' ).css( 'left' , _tbcontainerleft );
									$( '.'+_currth).css({ 'width' : _tbthwidth });
								}
								preventDefault
							});
							$(document).on( 'mouseup' , function(){
								_linemove = false;
								if ( !_linemove ) {
									$( '.drag_line' ).remove();
									$( '.page-container' ).removeClass( 'stopto_select' );
								}
							});
						}
						/* 自定义函数 */
						if ($.isFunction(freestyle)) {
							freestyle();
						}
				/*	}
				}*/
			});
		}
	}
	/* 隐藏列 */
	function hide_column ( self ) {
		var arrcookie = document.cookie.split( ';' );
		if ( arrcookie.length > 0 ) {
			for ( var c = 0 ; c < arrcookie.length ; c++ ) {
				var d = arrcookie[ c ].split( '=' );
				if( d[ 1 ] == 0 ){
					self.find( d[ 0 ] ).hide();
				}
			}
		}
	}
	/* 缓存隐藏和排好顺序的列 */
	function issavecookie( _column , obj ){
		var exp = new Date(); 
		exp.setTime(exp.getTime() + 30*360*60*60*1000);
		document.cookie = _column + '=' + obj + ';' + 'expires=' + exp.toGMTString();
	}
	/* 分页
	 * 是一个大型操作组合，这里不划分它的内部小操作，但是分页信息会和 iBuildtable 紧密结合，点击页按钮，table 中的 tr 会被重建，重建之后的 tr 继承之前的所有属性
	 * 取最大页值 我定义表格分页为 页面显示（如果100是最后一页）  1 2 3 4 5 ... 100  点击第 5 页 之后变为 5 6 7 8 9 ... 100 以此类推 
	 * 此种状态下如果再次点击第 5 页 变更为 1 2 3 4 5 ... 100 以此类推
	 * 当剩余数和末页之差小于 7 则 显示为 94 95 96 97 98 99 100
	 * 此时点击第 94 页 为 90 91 92 93 94 ... 100
	 * 当此页元素值与 1 之差小于 7 显示为 最初始状态
	 * 语言能力有限，差不多就这个意思
	 * pageindex 所查出信息共多少页
	 */
	function view_pagination( table_parameter_allcount , _isrolenumber , obj , _ajaxURL , _jsonstr , _jsonname , checktabletd , tabletools_columntd ) {
		obj.parent().find( '.table_execute_bottomcontainer' ).remove();
		var pageindex =  Math.ceil( table_parameter_allcount/_isrolenumber ),
			pagearr = new Array();
		if ( pageindex > 7 ) {
			pagearr[ 0 ] = '<span class="table_pageindex activation">1</span>';
			pagearr[ 1 ] = '<span class="table_pageindex">2</span>';
			pagearr[ 2 ] = '<span class="table_pageindex">3</span>';
			pagearr[ 3 ] = '<span class="table_pageindex">4</span>';
			pagearr[ 4 ] = '<span class="table_pageindex">5</span>';
			pagearr[ 5 ] = '<span class="table_pageindex">...</span>';
			pagearr[ 6 ] = '<span class="table_pageindex">' + pageindex + '</span>';
		} else {
			for ( var c = 0 ; c < pageindex ; c++ ) {
				pagearr[ c ] = '<span class="table_pageindex">' + parseInt( c + 1 ) + '</span>';
			}
		}
		var table_execute_bottomcontainer = '<div class="table_execute_bottomcontainer"><div class="table_all_index">共 ' + table_parameter_allcount + ' 条</div><div class="table_page_index"></div><div class="table_page_execute"><span class="table_page table_page_left"><i class="icon-angle-left"></i></span>' + pagearr.join( '' ) + '<span class="table_page table_page_right"><i class="icon-angle-right"></i></span></div><div class="jumpto_index">跳转</div><div class="pageindex_column">显示<div class="dropdown pageindex_column_select dropup"><a class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" role="button" aria-expanded="true"><input class="form-control" type="text" value="' + _isrolenumber + '"><span class="caret"></span></a><ul class="dropdown-menu" aria-labelledby="dropdownMenu1" role="menu"><li role="presentation"><a role="menuitem">10</a></li><li role="presentation"><a role="menuitem">25</a></li><li role="presentation"><a role="menuitem">50</a></li><li role="presentation"><a role="menuitem">100</a></li><li role="presentation"><a role="menuitem">' + table_parameter_allcount + '</a></li></ul></div></div></div>';
		obj.parent().append( table_execute_bottomcontainer );
		toolseparatepage( obj , pageindex );
		/* 点击选定每页显示条数 */
		obj.parent().find( '.dropdown.pageindex_column_select > ul > li > a' ).on( 'click' , function(){
			var _self = $( this ),
				_acr = $( this ).text();
			$( this ).parent().parent().parent().find( '.form-control' ).attr( 'value' , _acr );
			/* 使用ajax发送请求 */
			//_jsonstr[ _jsonstr.length-1 ] = _acr;
			/*xmlhttp.open( 'post' , _ajaxURL , true )
			xmlhttp.setRequestHeader( 'Content-Type' , 'application/x-www-form-urlencoded' );
			xmlhttp.send( _jsonname + '=' + _jsonstr );
			xmlhttp.onreadystatechange = function(){
				if ( xmlhttp.readyState == 4 && xmlhttp.status == 200 ) {
					var result = eval( xmlhttp.responseText );*/
					/***************//* 这里针对普通表格的数据测试，下拉表格暂不做处理，如果是和程序嵌套则删除此参数，使用后台返回参数适用所有情况 */
					var table_arr = new Array();
						var table_arr_1 = new Array(),
						table_arr_2 = new Array();
						/* 这里显示条数为用户所选条数，但是实际查询可能会小于这个数，以实际取回数据中的所有条数为准，要判断是否大于所有条数，若是大于则 i<allcount */
						for ( var i = 0 ; i < parseInt( _acr ) + 1 ; i++ ) {
							for ( var c = 0 ; c < 10 ; c++  ) {
								if ( c == 0 ) {
									table_arr_1[ i*10 ] = i;
								} else {
									table_arr_1[ i*10 + c ] = '测试' + i;
								}
							}
						}
						table_arr_2 = ['10','100'];
						table_arr = table_arr_1.concat( table_arr_2 );				
					/***************/
					/* 开始拆分从后台取出的数组 */
					var separate_list = table_separate_list( table_arr ), /* 参数赋值 */
						table_column = separate_list._table_column,		/* table 共几列 除了必显列外由数据库中读出的数据列 */
						table_parameter_th = separate_list._table_parameter_th,			/* table 表头 */
						table_parameter_td = separate_list._table_parameter_td,	/* table td 内容 */
						table_parameter_allcount = separate_list._table_parameter_allcount;	/* 数据库中共有多少行被查出的信息 */
					/* 开始重建表格内容，这里注意的是重建表格内容是不包括表头的,仅仅重建tbody中的内容 */
					build_table_tbody(  table_column , table_parameter_td.length/table_column , table_parameter_td , checktabletd , tabletools_columntd , _self.parent().parent().parent().parent().parent().parent().find( '.table.table_fun' ) );
					/* 重排分页栏 */ /* 这里需要大幅度简化，由于时间问题暂时不做处理 */
					obj.parent().find( '.table_execute_bottomcontainer .table_page_execute' ).remove();
					var pageindex =  Math.ceil( table_parameter_allcount/_acr ),
						pagearr = new Array();
					if ( pageindex > 7 ) {
						pagearr[ 0 ] = '<span class="table_pageindex">1</span>';
						pagearr[ 1 ] = '<span class="table_pageindex">2</span>';
						pagearr[ 2 ] = '<span class="table_pageindex">3</span>';
						pagearr[ 3 ] = '<span class="table_pageindex">4</span>';
						pagearr[ 4 ] = '<span class="table_pageindex">5</span>';
						pagearr[ 5 ] = '<span class="table_pageindex">...</span>';
						pagearr[ 6 ] = '<span class="table_pageindex">' + pageindex + '</span>';
					} else {
						for ( var c = 0 ; c < pageindex ; c++ ) {
							pagearr[ c ] = '<span class="table_pageindex">' + parseInt( c + 1 ) + '</span>';
						}
					}
					var separate_page = '<div class="table_page_execute"><span class="table_page table_page_left"><i class="icon-angle-left"></i></span>' + pagearr.join( '' ) + '<span class="table_page table_page_right"><i class="icon-angle-right"></i></span></div>';
					obj.parent().children().eq( 2 ).children().eq( 1 ).after( separate_page );
					toolseparatepage( obj , pageindex );
				/*}
			}*/
		});
	}
	/* 分页操作 */
	function toolseparatepage( obj , pageindex ){
		/* 点击页按钮开始分页操作 */
		obj.parent().find( '.table_page_execute' ).children().each( function( i ){
			$( this ).on( 'click' , function(){
				var self = $( this ),
					ann = new Array(),
					k = parseInt( self.text() );
				/* 前后两个为首页和尾页元素 */
				/* 当点击页码的第 1 个元素时 */
				if( i == 1 ) {
					if ( k > 1 ) {
						for ( var m = 0 , q = 6 ; m < 7 , q >= 0 ; m++ , q-- ) {
							if ( k > 5 ) {
								ann[ m ] = k - q;
							} else {
								ann[ 0 ] = 1;
								ann[ 1 ] = 2;
								ann[ 2 ] = 3;
								ann[ 3 ] = 4;
								ann[ 4 ] = 5;
								ann[ 5 ] = '...';
								ann[ 6 ] = pageindex;
							}
						}
					} else {
						ann[ 0 ] = 1;
						ann[ 1 ] = 2;
						ann[ 2 ] = 3;
						ann[ 3 ] = 4;
						ann[ 4 ] = 5;
						ann[ 5 ] = '...';
						ann[ 6 ] = pageindex;
					}
					for ( var j = 0 ; j < 7 ; j++ ) {
						self.parent().find( '.table_pageindex' ).eq( j ).html( ann[ j ] );
					}
				}
				/* 当点击页码的末元素时 */
				if ( i == 7 ) {
					for ( var m = 0 , q = 6 ; m < 7 , q >= 0; m++ , q-- ) {
						self.parent().find( '.table_pageindex' ).eq( m ).html( pageindex - q );
					}
				}
				/* 当点击页码的第 5 个元素时 */
				if ( i == 5 && self.parent().find( '.table_pageindex' ).eq( 5 ).text() == '...' ) {
					if ( k + 4 <= pageindex ) {
						for ( var m = 0 , n = k ; m < 4 , n < k + 5 ; m++ , n++ ) {
							ann[ m ] = n;
							if ( m == 4 ) {
								ann[ m + 1 ] ='...';
								ann[ m + 2 ] = pageindex;
							}
						}
					} else {
						for ( var m = 0 , n = k ; m < 6 , n < z + 1 ; m++ , n++ ) {
							ann[ m ] = n;
							if ( m == 4 ) {
								ann[ m + 1 ] = '...';
								ann[ m + 2 ] = pageindex;
							}
						}
					}
					for ( var j = 0 , q= 6 ; j < 7 , q > 0 ; j++ , q-- ) {
						if ( ann.length == 7) {
							self.parent().find( '.table_pageindex' ).eq( j ).html( ann[ j ] );
						} else if ( ann.length < 7 ) {
							self.parent().find( '.table_pageindex' ).eq( j ).html( pageindex - q );
						}
					}
				}
				self.parent().find( '.table_pageindex' ).removeClass( 'activation' );
				for ( var  m = 0 ; m < 7 ; m++ ) {
					if ( k == self.parent().find( '.table_pageindex' ).eq( m ).text() ) {
						self.parent().find( '.table_pageindex' ).eq( m ).addClass( 'activation' );
					}
				}
				/*xmlhttp.open( 'post' , _ajaxURL , true );
				xmlhttp.setRequestHeader( "Content-Type" , "application/x-www-form-urlencoded" );
				xmlhttp.send( _jsonname + '=' + _jsonstr );
				xmlhttp.onreadystatechange = function(){
					if ( xmlhttp.readyState == 4 && xmlhttp.status == 200 ) {
						var result = eval( xmlhttp.responseText );*/
						
					/*}
				}*/
			});
		});
	}
	/* 建表 */
	function build_table(  table_column , table_parameter_th , checktableth , tabletools_columnth , table_role , table_parameter_td , checktabletd , tabletools_columntd , obj ) {
		var thlist = new Array(),		/* 此表格显示的 th 元素集合 */
			trlist = new Array();		/* 此表格显示的 tr 元素集合 */
		/* 表头 1行 */
		for ( var d = 0 ; d < table_column ; d++ ) {
			if ( d == 0 ) {
				thlist[ d ] = '<thead><tr><th class="table_hidden_id">' + table_parameter_th[ d ] + '</th><th class="table_serial">序号</th>' + checktableth;
			} else if ( d == table_column - 1 ) {
				thlist[ d ] = '<th class="column_' + d + '">' + table_parameter_th[ d ] + '</th>' + tabletools_columnth + '</tr></thead>';
			} else {
				thlist[ d ] = '<th class="column_' + d + '">' + table_parameter_th[ d ] + '</th>';
			}
		}
		/* 内容 */
		for ( var c = 0 ; c < table_role ; c++ ) {
			for ( var d = 0 ; d < table_column ; d++ ) {
				if ( d == 0 ) {
					trlist[ c*table_column ] = '<tr><td class="table_hidden_id">' + table_parameter_td[ c*table_column ] + '</td><td class="table_serial">' + parseInt( c + 1 ) + '</td>' + checktabletd;
				} else if ( d == table_column - 1 ) {
					trlist[ c*table_column + d ] = '<td class="column_' + d + '">' + table_parameter_td[ c*table_column + d ] + '</td>' + tabletools_columntd + '</tr>';
				} else {
					trlist[ c*table_column + d ] ='<td class="column_'+ d +'">' + table_parameter_td[ c*table_column + d ] + '</td>';
				}
			}
		}
		obj.html( thlist.join( '' ) + '<tbody>' + trlist.join( '' ) + '</tbody>' );	/* 把动态生成的信息编译到表格中 */
	}
	/* 拆分后台查出的json数组 */
	function table_separate_list( obj ){
		var _table_column = parseInt( obj[ obj.length - 2 ] ),		/* table 共几列 除了必显列外由数据库中读出的数据列 */
			_table_parameter_th = obj.slice( 0 , _table_column ),			/* table 表头 */
			_table_parameter_td = obj.slice( _table_column , obj.length - 2 ),	/* table td 内容 */
			_table_parameter_allcount = obj[ obj.length - 1 ];	/* 数据库中共有多少行被查出的信息 */
		return {
			_table_column : _table_column,
			_table_parameter_th : _table_parameter_th,
			_table_parameter_td : _table_parameter_td,
			_table_parameter_allcount : _table_parameter_allcount
		}
	}
	/* 不包含表头的数组拆分 */
	function table_separate_tbodylist( obj ){
		var _table_column = parseInt( obj[ obj.length - 2 ] ),		/* table 共几列 除了必显列外由数据库中读出的数据列 */
			_table_parameter_td = obj.slice( 0 , obj.length - 2 ),	/* table td 内容 */
			_table_parameter_allcount = obj[ obj.length - 1 ];	/* 数据库中共有多少行被查出的信息 */
		return {
			_table_column : _table_column,
			_table_parameter_td : _table_parameter_td,
			_table_parameter_allcount : _table_parameter_allcount
		}
	}
	/* 仅仅创建tbody中的内容 */ /* 参数 ： 列数 行数 td集合 复选框 行工具 对象  */
	function build_table_tbody(  table_column , table_role , table_parameter_td , checktabletd , tabletools_columntd , obj ) {
		var trlist = new Array();		/* 此表格显示的 tr 元素集合 */
		/* 内容 */
		for ( var c = 0 ; c < table_role ; c++ ) {
			for ( var d = 0 ; d < table_column ; d++ ) {
				if ( d == 0 ) {
					trlist[ c*table_column ] = '<tr><td class="table_hidden_id">' + table_parameter_td[ c*table_column ] + '</td><td class="table_serial">' + parseInt( c + 1 ) + '</td>' + checktabletd;
				} else if ( d == table_column - 1 ) {
					trlist[ c*table_column + d ] = '<td class="column_' + d + '">' + table_parameter_td[ c*table_column + d ] + '</td>' + tabletools_columntd + '</tr>';
				} else {
					trlist[ c*table_column + d ] ='<td class="column_'+ d +'">' + table_parameter_td[ c*table_column + d ] + '</td>';
				}
			}
		}
		obj.children().eq( 1 ).html( trlist.join( '' ) );	/* 把动态生成的信息编译到表格中 */
		$( 'input' ).iCheck();
		$( '.table_checkbox' ).off( 'click' );
		hide_column( obj );
	}
	/* 表格的查询功能 */ /* 参数 ： 对象 每页显示条数 td内容 复选框 行工具条 ajx路径 请求参数 json名称 */
	function tablesearchajax ( obj ,rolenumber , table_parameter_td , checktabletd ,tabletools_columntd , _ajaxURL , _jsonstr , _jsonname ) {
		/* 此处使用ajax发送请求 接口函暂时不使用 */
		/*xmlhttp.open( 'post' , _ajaxURL , true );
		xmlhttp.setRequestHeader( 'Content-Type' , 'application/x-www-form-urlencoded' );
		xmlhttp.send( _jsonname + '=' + _jsonstr );
		xmlhttp.onreadystatechange = function(){
			if ( xmlhttp.readyState == 4 && xmlhttp.status == 200 ) {
				var result = eval( xmlhttp.responseText );*/
				/* 这里使用ajax查询信息和定义查询到信息之后的操作 */
				/**************/
				var arr = ['1','测试1','测试1','测试1','测试1','5','60'],
				/**************/
					separate_list = table_separate_tbodylist(arr),  		/* 参数赋值 */
					table_column = separate_list._table_column,				/* table 共几列 除了必显列外由数据库中读出的数据列 */
					table_parameter_th = separate_list._table_parameter_th,	/* table 表头 */
					table_parameter_td = separate_list._table_parameter_td,	/* table td 内容 */
					table_parameter_allcount = separate_list._table_parameter_allcount;	/* 数据库中共有多少行被查出的信息 */
					/* 由于是模拟，所以它并没有查出100行，只用一行测试，小于每页显示行，为了测试用除法 */
				build_table_tbody(  table_column , table_parameter_td.length/table_column , table_parameter_td , checktabletd , tabletools_columntd , obj );
				view_pagination( table_parameter_allcount , rolenumber , obj , _ajaxURL , _jsonstr , _jsonname , checktabletd , tabletools_columntd );
		/*	}
		}*/
	}
	/* iBuildtable END */
	
	
	
	
	
	/* iScrollmenu BEGIN */ /* ui页展示菜单使其一直保持在可见状态 */
	var _iScrollmenu = 'iScrollmenu';
	$.fn[ _iScrollmenu ] = function () {
		var stack = $(),
			worker = function ( object ) {
				object.each( function () {
					var self = $( this );
					stack = stack.add( self );
				})
			};
		worker( this );
		return stack.each( function () {
			var self = $( this );
			$( window ).scroll( function () {
				var _distance = self.offset().top,
					_documentscroll = $( document ).scrollTop();
				if ( _documentscroll - _distance > -55 && _documentscroll - _distance != 0 ) {
					self.children().css({ 'position':'fixed' , 'top':'60px' });
				} else if ( _documentscroll - _distance > -138 ) {
					self.children().css({ 'position':'relative','top':'0' });
				}
			})
		});
	}
	/* iScrollmenu END */
	
	
	
	
	/* datetimepicker BEGIN */ /* 选择时间 精确到分钟，但是分钟元素过多，此插件以5分钟为间隔选择，如果要精确到每一分钟和每一秒，自定义输入框填写 */
	/* 当我使用font-awesome时，插件内部功能错乱，我将参数改为能适应font-awesome的，其次原插件在计算距离上出现了问题，这些都与改变字体参数有关，我在组件外围加一个自定义容器后，距离在chorme下比moz大了2倍 控制css top的函数为 place  */
	/* bootstrap-datetimepicker.js
	 * Copyright 2012 Stefan Petre
	 * Improvements by Andrew Rowls
	 * Improvements by Sébastien Malot
	 * Improvements by Yun Lai
	 * Improvements by Kenneth Henderick
	 * Project URL : http://www.malot.fr/bootstrap-datetimepicker
	 * Licensed under the Apache License, Version 2.0 (the "License");
	 * you may not use this file except in compliance with the License.
	 * You may obtain a copy of the License at
	 * http://www.apache.org/licenses/LICENSE-2.0
	 * Unless required by applicable law or agreed to in writing, software
	 * distributed under the License is distributed on an "AS IS" BASIS,
	 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	 * See the License for the specific language governing permissions and
	 * limitations under the License.
	 * Improvement by CuGBabyBeaR @ 2013-09-12
	 * Make it work in bootstrap v2
	 */

	function UTCDate() {
		return new Date(Date.UTC.apply(Date, arguments));
	}
	function UTCToday() {
		var today = new Date();
		return UTCDate(today.getUTCFullYear(), today.getUTCMonth(), today.getUTCDate(), today.getUTCHours(), today.getUTCMinutes(), today.getUTCSeconds(), 0);
	}
	// Picker object
	var Datetimepicker = function (element, options) {
		var that = this;
		this.element = $(element);
		// add container for single page application
		// when page switch the datetimepicker div will be removed also.
		this.container = options.container || 'body';
		this.language = options.language || this.element.data('date-language') || "en";
		this.language = this.language in dates ? this.language : "en";
		this.isRTL = dates[this.language].rtl || false;
		this.formatType = options.formatType || this.element.data('format-type') || 'standard';
		this.format = DPGlobal.parseFormat(options.format || this.element.data('date-format') || dates[this.language].format || DPGlobal.getDefaultFormat(this.formatType, 'input'), this.formatType);
		this.isInline = false;
		this.isVisible = false;
		this.isInput = this.element.is('input');
		this.fontAwesome = options.fontAwesome || this.element.data('font-awesome') || false;
		this.bootcssVer = options.bootcssVer || (this.isInput ? (this.element.is('.form-control') ? 3 : 2) : ( this.bootcssVer = this.element.is('.input-group') ? 3 : 2 ));
		this.component = this.element.is('.date') ? ( this.bootcssVer == 3 ? this.element.find('.input-group-addon .icon-th, .input-group-addon .icon-time, .input-group-addon .icon-calendar, .input-group-addon .icon-calendar, .input-group-addon .fa-calendar, .input-group-addon .fa-clock-o').parent() : this.element.find('.add-on .icon-th, .add-on .icon-time, .add-on .icon-calendar .fa-calendar .fa-clock-o').parent()) : false;
		this.componentReset = this.element.is('.date') ? ( this.bootcssVer == 3 ? this.element.find('.input-group-addon .icon-remove .fa-times').parent() : this.element.find('.add-on .icon-remove .fa-times').parent()) : false;
		this.hasInput = this.component && this.element.find('input').length;
		if (this.component && this.component.length === 0) {
			this.component = false;
		}
		this.linkField = options.linkField || this.element.data('link-field') || false;
		this.linkFormat = DPGlobal.parseFormat(options.linkFormat || this.element.data('link-format') || DPGlobal.getDefaultFormat(this.formatType, 'link'), this.formatType);
		this.minuteStep = options.minuteStep || this.element.data('minute-step') || 5;
		this.pickerPosition = options.pickerPosition || this.element.data('picker-position') || 'bottom-right';
		this.showMeridian = options.showMeridian || this.element.data('show-meridian') || false;
		this.initialDate = options.initialDate || new Date();
		this.zIndex = options.zIndex || this.element.data('z-index') || undefined;
		this.icons = {
			leftArrow: this.fontAwesome ? 'fa-arrow-left' : (this.bootcssVer === 3 ? 'icon-arrow-left' : 'icon-arrow-left'),
			rightArrow: this.fontAwesome ? 'fa-arrow-right' : (this.bootcssVer === 3 ? 'icon-arrow-right' : 'icon-arrow-right')
		};
		this.icontype = this.fontAwesome ? 'fa' : 'icon';
		this._attachEvents();
		this.formatViewType = "datetime";
		if ('formatViewType' in options) {
			this.formatViewType = options.formatViewType;
		} else if ('formatViewType' in this.element.data()) {
			this.formatViewType = this.element.data('formatViewType');
		}
		this.minView = 0;
		if ('minView' in options) {
			this.minView = options.minView;
		} else if ('minView' in this.element.data()) {
			this.minView = this.element.data('min-view');
		}
		this.minView = DPGlobal.convertViewMode(this.minView);
		this.maxView = DPGlobal.modes.length - 1;
		if ('maxView' in options) {
			this.maxView = options.maxView;
		} else if ('maxView' in this.element.data()) {
			this.maxView = this.element.data('max-view');
		}
		this.maxView = DPGlobal.convertViewMode(this.maxView);

		this.wheelViewModeNavigation = false;
		if ('wheelViewModeNavigation' in options) {
			this.wheelViewModeNavigation = options.wheelViewModeNavigation;
		} else if ('wheelViewModeNavigation' in this.element.data()) {
			this.wheelViewModeNavigation = this.element.data('view-mode-wheel-navigation');
		}
		this.wheelViewModeNavigationInverseDirection = false;
		if ('wheelViewModeNavigationInverseDirection' in options) {
			this.wheelViewModeNavigationInverseDirection = options.wheelViewModeNavigationInverseDirection;
		} else if ('wheelViewModeNavigationInverseDirection' in this.element.data()) {
			this.wheelViewModeNavigationInverseDirection = this.element.data('view-mode-wheel-navigation-inverse-dir');
		}
		this.wheelViewModeNavigationDelay = 100;
		if ('wheelViewModeNavigationDelay' in options) {
			this.wheelViewModeNavigationDelay = options.wheelViewModeNavigationDelay;
		} else if ('wheelViewModeNavigationDelay' in this.element.data()) {
			this.wheelViewModeNavigationDelay = this.element.data('view-mode-wheel-navigation-delay');
		}
		this.startViewMode = 2;
		if ('startView' in options) {
			this.startViewMode = options.startView;
		} else if ('startView' in this.element.data()) {
			this.startViewMode = this.element.data('start-view');
		}
		this.startViewMode = DPGlobal.convertViewMode(this.startViewMode);
		this.viewMode = this.startViewMode;
		this.viewSelect = this.minView;
		if ('viewSelect' in options) {
			this.viewSelect = options.viewSelect;
		} else if ('viewSelect' in this.element.data()) {
			this.viewSelect = this.element.data('view-select');
		}
		this.viewSelect = DPGlobal.convertViewMode(this.viewSelect);
		this.forceParse = true;
		if ('forceParse' in options) {
			this.forceParse = options.forceParse;
		} else if ('dateForceParse' in this.element.data()) {
			this.forceParse = this.element.data('date-force-parse');
		}
		var template = this.bootcssVer === 3 ? DPGlobal.templateV3 : DPGlobal.template;
		while (template.indexOf('{iconType}') !== -1) {
			template = template.replace('{iconType}', this.icontype);
		}
		while (template.indexOf('{leftArrow}') !== -1) {
			template = template.replace('{leftArrow}', this.icons.leftArrow);
		}
		while (template.indexOf('{rightArrow}') !== -1) {
			template = template.replace('{rightArrow}', this.icons.rightArrow);
		}
		this.picker = $(template)
			.appendTo(this.isInline ? this.element : this.container) // 'body')
			.on({
				click:     $.proxy(this.click, this),
				mousedown: $.proxy(this.mousedown, this)
			});
		if (this.wheelViewModeNavigation) {
			if ($.fn.mousewheel) {
				this.picker.on({mousewheel: $.proxy(this.mousewheel, this)});
			} else {
				console.log("Mouse Wheel event is not supported. Please include the jQuery Mouse Wheel plugin before enabling this option");
			}
		}
		if (this.isInline) {
			this.picker.addClass('datetimepicker-inline');
		} else {
			this.picker.addClass('datetimepicker-dropdown-' + this.pickerPosition + ' dropdown-menu');
		}
		if (this.isRTL) {
			this.picker.addClass('datetimepicker-rtl');
			var selector = this.bootcssVer === 3 ? '.prev span, .next span' : '.prev i, .next i';
			this.picker.find(selector).toggleClass(this.icons.leftArrow + ' ' + this.icons.rightArrow);
		}
		$(document).on('mousedown', function (e) {
			// Clicked outside the datetimepicker, hide it
			if ($(e.target).closest('.datetimepicker').length === 0) {
				that.hide();
			}
		});
		this.autoclose = false;
		if ('autoclose' in options) {
			this.autoclose = options.autoclose;
		} else if ('dateAutoclose' in this.element.data()) {
			this.autoclose = this.element.data('date-autoclose');
		}
		this.keyboardNavigation = true;
		if ('keyboardNavigation' in options) {
			this.keyboardNavigation = options.keyboardNavigation;
		} else if ('dateKeyboardNavigation' in this.element.data()) {
			this.keyboardNavigation = this.element.data('date-keyboard-navigation');
		}
		this.todayBtn = (options.todayBtn || this.element.data('date-today-btn') || false);
		this.todayHighlight = (options.todayHighlight || this.element.data('date-today-highlight') || false);
		this.weekStart = ((options.weekStart || this.element.data('date-weekstart') || dates[this.language].weekStart || 0) % 7);
		this.weekEnd = ((this.weekStart + 6) % 7);
		this.startDate = -Infinity;
		this.endDate = Infinity;
		this.daysOfWeekDisabled = [];
		this.setStartDate(options.startDate || this.element.data('date-startdate'));
		this.setEndDate(options.endDate || this.element.data('date-enddate'));
		this.setDaysOfWeekDisabled(options.daysOfWeekDisabled || this.element.data('date-days-of-week-disabled'));
		this.setMinutesDisabled(options.minutesDisabled || this.element.data('date-minute-disabled'));
		this.setHoursDisabled(options.hoursDisabled || this.element.data('date-hour-disabled'));
		this.fillDow();
		this.fillMonths();
		this.update();
		this.showMode();
		if (this.isInline) {
			this.show();
		}
	};
	Datetimepicker.prototype = {
		constructor: Datetimepicker,
		_events:       [],
		_attachEvents: function () {
			this._detachEvents();
			if (this.isInput) { 
				this._events = [
					[this.element, {
						focus:   $.proxy(this.show, this),
						keyup:   $.proxy(this.update, this),
						keydown: $.proxy(this.keydown, this)
					}]
				];
			} else if (this.component && this.hasInput) { // component: input + button
				this._events = [
					// For components that are not readonly, allow keyboard nav
					[this.element.find('input'), {
						focus:   $.proxy(this.show, this),
						keyup:   $.proxy(this.update, this),
						keydown: $.proxy(this.keydown, this)
					}],
					[this.component, {
						click: $.proxy(this.show, this)
					}]
				];
				if (this.componentReset) {
					this._events.push([
						this.componentReset,
						{click: $.proxy(this.reset, this)}
					]);
				}
			} else if (this.element.is('div')) {  // inline datetimepicker
				this.isInline = true;
			}
			else {
				this._events = [
					[this.element, {
						click: $.proxy(this.show, this)
					}]
				];
			}
			for (var i = 0, el, ev; i < this._events.length; i++) {
				el = this._events[i][0];
				ev = this._events[i][1];
				el.on(ev);
			}
		},
		_detachEvents: function () {
			for (var i = 0, el, ev; i < this._events.length; i++) {
				el = this._events[i][0];
				ev = this._events[i][1];
				el.off(ev);
			}
			this._events = [];
		},
		show: function (e) {
			this.picker.show();
			this.height = this.component ? this.component.outerHeight() : this.element.outerHeight();
			if (this.forceParse) {
				this.update();
			}
			this.place();
			$(window).on('resize', $.proxy(this.place, this));
			if (e) {
				e.stopPropagation();
				e.preventDefault();
			}
			this.isVisible = true;
			this.element.trigger({
				type: 'show',
				date: this.date
			});
		},
		hide: function (e) {
			if (!this.isVisible) return;
			if (this.isInline) return;
			this.picker.hide();
			$(window).off('resize', this.place);
			this.viewMode = this.startViewMode;
			this.showMode();
			if (!this.isInput) {
				$(document).off('mousedown', this.hide);
			}
			if (
				this.forceParse &&
					(
						this.isInput && this.element.val() ||
							this.hasInput && this.element.find('input').val()
						)
				)
				this.setValue();
			this.isVisible = false;
			this.element.trigger({
				type: 'hide',
				date: this.date
			});
		},
		remove: function () {
			this._detachEvents();
			this.picker.remove();
			delete this.picker;
			delete this.element.data().datetimepicker;
		},
		getDate: function () {
			var d = this.getUTCDate();
			return new Date(d.getTime() + (d.getTimezoneOffset() * 60000));
		},
		getUTCDate: function () {
			return this.date;
		},
		setDate: function (d) {
			this.setUTCDate(new Date(d.getTime() - (d.getTimezoneOffset() * 60000)));
		},
		setUTCDate: function (d) {
			if (d >= this.startDate && d <= this.endDate) {
				this.date = d;
				this.setValue();
				this.viewDate = this.date;
				this.fill();
			} else {
				this.element.trigger({
					type:      'outOfRange',
					date:      d,
					startDate: this.startDate,
					endDate:   this.endDate
				});
			}
		},
		setFormat: function (format) {
			this.format = DPGlobal.parseFormat(format, this.formatType);
			var element;
			if (this.isInput) {
				element = this.element;
			} else if (this.component) {
				element = this.element.find('input');
			}
			if (element && element.val()) {
				this.setValue();
			}
		},
		setValue: function () {
			var formatted = this.getFormattedDate();
			if (!this.isInput) {
				if (this.component) {
					this.element.find('input').val(formatted);
				}
				this.element.data('date', formatted);
			} else {
				this.element.val(formatted);
			}
			if (this.linkField) {
				$('#' + this.linkField).val(this.getFormattedDate(this.linkFormat));
			}
		},
		getFormattedDate: function (format) {
			if (format == undefined) format = this.format;
			return DPGlobal.formatDate(this.date, format, this.language, this.formatType);
		},
		setStartDate: function (startDate) {
			this.startDate = startDate || -Infinity;
			if (this.startDate !== -Infinity) {
				this.startDate = DPGlobal.parseDate(this.startDate, this.format, this.language, this.formatType);
			}
			this.update();
			this.updateNavArrows();
		},
		setEndDate: function (endDate) {
			this.endDate = endDate || Infinity;
			if (this.endDate !== Infinity) {
				this.endDate = DPGlobal.parseDate(this.endDate, this.format, this.language, this.formatType);
			}
			this.update();
			this.updateNavArrows();
		},
		setDaysOfWeekDisabled: function (daysOfWeekDisabled) {
			this.daysOfWeekDisabled = daysOfWeekDisabled || [];
			if (!$.isArray(this.daysOfWeekDisabled)) {
				this.daysOfWeekDisabled = this.daysOfWeekDisabled.split(/,\s*/);
			}
			this.daysOfWeekDisabled = $.map(this.daysOfWeekDisabled, function (d) {
				return parseInt(d, 10);
			});
			this.update();
			this.updateNavArrows();
		},
		setMinutesDisabled: function (minutesDisabled) {
			this.minutesDisabled = minutesDisabled || [];
			if (!$.isArray(this.minutesDisabled)) {
				this.minutesDisabled = this.minutesDisabled.split(/,\s*/);
			}
			this.minutesDisabled = $.map(this.minutesDisabled, function (d) {
				return parseInt(d, 10);
			});
			this.update();
			this.updateNavArrows();
		},
		setHoursDisabled: function (hoursDisabled) {
			this.hoursDisabled = hoursDisabled || [];
			if (!$.isArray(this.hoursDisabled)) {
				this.hoursDisabled = this.hoursDisabled.split(/,\s*/);
			}
			this.hoursDisabled = $.map(this.hoursDisabled, function (d) {
				return parseInt(d, 10);
			});
			this.update();
			this.updateNavArrows();
		},
		place: function () {
			if (this.isInline) return;

			if (!this.zIndex) {
				var index_highest = 0;
				$('div').each(function () {
					var index_current = parseInt($(this).css("zIndex"), 10);
					if (index_current > index_highest) {
						index_highest = index_current;
					}
				});
				this.zIndex = index_highest + 10;
			}
			var offset, top, left, containerOffset;
			if (this.container instanceof $) {
				containerOffset = this.container.offset();
			} else {
				containerOffset = $(this.container).offset();
			}
			if (this.component) {
				offset = this.component.offset();
				left = offset.left;
				if (this.pickerPosition == 'bottom-left' || this.pickerPosition == 'top-left') {
					left += this.component.outerWidth() - this.picker.outerWidth();
				}
			} else {
				offset = this.element.offset();
				left = offset.left;
			}
			if(left+220 > document.body.clientWidth){
            		left = document.body.clientWidth-220;
          		}
			if (this.pickerPosition == 'top-left' || this.pickerPosition == 'top-right') {
				top = offset.top - this.picker.outerHeight();
			} else {
				top = offset.top + this.height;
			}
			top = top - containerOffset.top;
			left = left - containerOffset.left;
			//top = top + document.body.scrollTop	//这句有问题，在谷歌和火狐下严重错位
			this.picker.css({
				top:    top + 45,
				left:   left,
				zIndex: this.zIndex
			});
		},
		update: function () {
			var date, fromArgs = false;
			if (arguments && arguments.length && (typeof arguments[0] === 'string' || arguments[0] instanceof Date)) {
				date = arguments[0];
				fromArgs = true;
			} else {
				date = (this.isInput ? this.element.val() : this.element.find('input').val()) || this.element.data('date') || this.initialDate;
				if (typeof date == 'string' || date instanceof String) {
				  date = date.replace(/^\s+|\s+$/g,'');
				}
			}
			if (!date) {
				date = new Date();
				fromArgs = false;
			}
			this.date = DPGlobal.parseDate(date, this.format, this.language, this.formatType);
			if (fromArgs) this.setValue();
			if (this.date < this.startDate) {
				this.viewDate = new Date(this.startDate);
			} else if (this.date > this.endDate) {
				this.viewDate = new Date(this.endDate);
			} else {
				this.viewDate = new Date(this.date);
			}
			this.fill();
		},
		fillDow: function () {
			var dowCnt = this.weekStart,
				html = '<tr>';
			while (dowCnt < this.weekStart + 7) {
				html += '<th class="dow">' + dates[this.language].daysMin[(dowCnt++) % 7] + '</th>';
			}
			html += '</tr>';
			this.picker.find('.datetimepicker-days thead').append(html);
		},
		fillMonths: function () {
			var html = '',
				i = 0;
			while (i < 12) {
				html += '<span class="month">' + dates[this.language].monthsShort[i++] + '</span>';
			}
			this.picker.find('.datetimepicker-months td').html(html);
		},
		fill: function () {
			if (this.date == null || this.viewDate == null) {
				return;
			}
			var d = new Date(this.viewDate),
				year = d.getUTCFullYear(),
				month = d.getUTCMonth(),
				dayMonth = d.getUTCDate(),
				hours = d.getUTCHours(),
				minutes = d.getUTCMinutes(),
				startYear = this.startDate !== -Infinity ? this.startDate.getUTCFullYear() : -Infinity,
				startMonth = this.startDate !== -Infinity ? this.startDate.getUTCMonth() + 1 : -Infinity,
				endYear = this.endDate !== Infinity ? this.endDate.getUTCFullYear() : Infinity,
				endMonth = this.endDate !== Infinity ? this.endDate.getUTCMonth() + 1 : Infinity,
				currentDate = (new UTCDate(this.date.getUTCFullYear(), this.date.getUTCMonth(), this.date.getUTCDate())).valueOf(),
				today = new Date();
			this.picker.find('.datetimepicker-days thead th:eq(1)')
				.text(dates[this.language].months[month] + ' ' + year);
			if (this.formatViewType == "time") {
				var formatted = this.getFormattedDate();
				this.picker.find('.datetimepicker-hours thead th:eq(1)').text(formatted);
				this.picker.find('.datetimepicker-minutes thead th:eq(1)').text(formatted);
			} else {
				this.picker.find('.datetimepicker-hours thead th:eq(1)')
					.text(dayMonth + ' ' + dates[this.language].months[month] + ' ' + year);
				this.picker.find('.datetimepicker-minutes thead th:eq(1)')
					.text(dayMonth + ' ' + dates[this.language].months[month] + ' ' + year);
			}
			this.picker.find('tfoot th.today')
				.text(dates[this.language].today)
				.toggle(this.todayBtn !== false);
			this.updateNavArrows();
			this.fillMonths();
			/*var prevMonth = UTCDate(year, month, 0,0,0,0,0);
			 prevMonth.setUTCDate(prevMonth.getDate() - (prevMonth.getUTCDay() - this.weekStart + 7)%7);*/
			var prevMonth = UTCDate(year, month - 1, 28, 0, 0, 0, 0),
				day = DPGlobal.getDaysInMonth(prevMonth.getUTCFullYear(), prevMonth.getUTCMonth());
			prevMonth.setUTCDate(day);
			prevMonth.setUTCDate(day - (prevMonth.getUTCDay() - this.weekStart + 7) % 7);
			var nextMonth = new Date(prevMonth);
			nextMonth.setUTCDate(nextMonth.getUTCDate() + 42);
			nextMonth = nextMonth.valueOf();
			var html = [];
			var clsName;
			while (prevMonth.valueOf() < nextMonth) {
				if (prevMonth.getUTCDay() == this.weekStart) {
					html.push('<tr>');
				}
				clsName = '';
				if (prevMonth.getUTCFullYear() < year || (prevMonth.getUTCFullYear() == year && prevMonth.getUTCMonth() < month)) {
					clsName += ' old';
				} else if (prevMonth.getUTCFullYear() > year || (prevMonth.getUTCFullYear() == year && prevMonth.getUTCMonth() > month)) {
					clsName += ' new';
				}
				// Compare internal UTC date with local today, not UTC today
				if (this.todayHighlight &&
					prevMonth.getUTCFullYear() == today.getFullYear() &&
					prevMonth.getUTCMonth() == today.getMonth() &&
					prevMonth.getUTCDate() == today.getDate()) {
					clsName += ' today';
				}
				if (prevMonth.valueOf() == currentDate) {
					clsName += ' active';
				}
				if ((prevMonth.valueOf() + 86400000) <= this.startDate || prevMonth.valueOf() > this.endDate ||
					$.inArray(prevMonth.getUTCDay(), this.daysOfWeekDisabled) !== -1) {
					clsName += ' disabled';
				}
				html.push('<td class="day' + clsName + '">' + prevMonth.getUTCDate() + '</td>');
				if (prevMonth.getUTCDay() == this.weekEnd) {
					html.push('</tr>');
				}
				prevMonth.setUTCDate(prevMonth.getUTCDate() + 1);
			}
			this.picker.find('.datetimepicker-days tbody').empty().append(html.join(''));
			html = [];
			var txt = '', meridian = '', meridianOld = '';
			var hoursDisabled = this.hoursDisabled || [];
			for (var i = 0; i < 24; i++) {
				if (hoursDisabled.indexOf(i) !== -1) continue;
				var actual = UTCDate(year, month, dayMonth, i);
				clsName = '';
				// We want the previous hour for the startDate
				if ((actual.valueOf() + 3600000) <= this.startDate || actual.valueOf() > this.endDate) {
					clsName += ' disabled';
				} else if (hours == i) {
					clsName += ' active';
				}
				if (this.showMeridian && dates[this.language].meridiem.length == 2) {
					meridian = (i < 12 ? dates[this.language].meridiem[0] : dates[this.language].meridiem[1]);
					if (meridian != meridianOld) {
						if (meridianOld != '') {
							html.push('</fieldset>');
						}
						html.push('<fieldset class="hour"><legend>' + meridian.toUpperCase() + '</legend>');
					}
					meridianOld = meridian;
					txt = (i % 12 ? i % 12 : 12);
					html.push('<span class="hour' + clsName + ' hour_' + (i < 12 ? 'am' : 'pm') + '">' + txt + '</span>');
					if (i == 23) {
						html.push('</fieldset>');
					}
				} else {
					txt = i + ':00';
					html.push('<span class="hour' + clsName + '">' + txt + '</span>');
				}
			}
			this.picker.find('.datetimepicker-hours td').html(html.join(''));
			html = [];
			txt = '', meridian = '', meridianOld = '';
			var minutesDisabled = this.minutesDisabled || [];
			for (var i = 0; i < 60; i += this.minuteStep) {
				if (minutesDisabled.indexOf(i) !== -1) continue;
				var actual = UTCDate(year, month, dayMonth, hours, i, 0);
				clsName = '';
				if (actual.valueOf() < this.startDate || actual.valueOf() > this.endDate) {
					clsName += ' disabled';
				} else if (Math.floor(minutes / this.minuteStep) == Math.floor(i / this.minuteStep)) {
					clsName += ' active';
				}
				if (this.showMeridian && dates[this.language].meridiem.length == 2) {
					meridian = (hours < 12 ? dates[this.language].meridiem[0] : dates[this.language].meridiem[1]);
					if (meridian != meridianOld) {
						if (meridianOld != '') {
							html.push('</fieldset>');
						}
						html.push('<fieldset class="minute"><legend>' + meridian.toUpperCase() + '</legend>');
					}
					meridianOld = meridian;
					txt = (hours % 12 ? hours % 12 : 12);
					//html.push('<span class="minute'+clsName+' minute_'+(hours<12?'am':'pm')+'">'+txt+'</span>');
					html.push('<span class="minute' + clsName + '">' + txt + ':' + (i < 10 ? '0' + i : i) + '</span>');
					if (i == 59) {
						html.push('</fieldset>');
					}
				} else {
					txt = i + ':00';
					//html.push('<span class="hour'+clsName+'">'+txt+'</span>');
					html.push('<span class="minute' + clsName + '">' + hours + ':' + (i < 10 ? '0' + i : i) + '</span>');
				}
			}
			this.picker.find('.datetimepicker-minutes td').html(html.join(''));
			var currentYear = this.date.getUTCFullYear();
			var months = this.picker.find('.datetimepicker-months')
				.find('th:eq(1)')
				.text(year)
				.end()
				.find('span').removeClass('active');
			if (currentYear == year) {
				// getUTCMonths() returns 0 based, and we need to select the next one
				months.eq(this.date.getUTCMonth() + 2).addClass('active');
			}
			if (year < startYear || year > endYear) {
				months.addClass('disabled');
			}
			if (year == startYear) {
				months.slice(0, startMonth + 1).addClass('disabled');
			}
			if (year == endYear) {
				months.slice(endMonth).addClass('disabled');
			}
			html = '';
			year = parseInt(year / 10, 10) * 10;
			var yearCont = this.picker.find('.datetimepicker-years')
				.find('th:eq(1)')
				.text(year + '-' + (year + 9))
				.end()
				.find('td');
			year -= 1;
			for (var i = -1; i < 11; i++) {
				html += '<span class="year' + (i == -1 || i == 10 ? ' old' : '') + (currentYear == year ? ' active' : '') + (year < startYear || year > endYear ? ' disabled' : '') + '">' + year + '</span>';
				year += 1;
			}
			yearCont.html(html);
			this.place();
		},
		updateNavArrows: function () {
			var d = new Date(this.viewDate),
				year = d.getUTCFullYear(),
				month = d.getUTCMonth(),
				day = d.getUTCDate(),
				hour = d.getUTCHours();
			switch (this.viewMode) {
				case 0:
					if (this.startDate !== -Infinity && year <= this.startDate.getUTCFullYear()
						&& month <= this.startDate.getUTCMonth()
						&& day <= this.startDate.getUTCDate()
						&& hour <= this.startDate.getUTCHours()) {
						this.picker.find('.prev').css({visibility: 'hidden'});
					} else {
						this.picker.find('.prev').css({visibility: 'visible'});
					}
					if (this.endDate !== Infinity && year >= this.endDate.getUTCFullYear()
						&& month >= this.endDate.getUTCMonth()
						&& day >= this.endDate.getUTCDate()
						&& hour >= this.endDate.getUTCHours()) {
						this.picker.find('.next').css({visibility: 'hidden'});
					} else {
						this.picker.find('.next').css({visibility: 'visible'});
					}
					break;
				case 1:
					if (this.startDate !== -Infinity && year <= this.startDate.getUTCFullYear()
						&& month <= this.startDate.getUTCMonth()
						&& day <= this.startDate.getUTCDate()) {
						this.picker.find('.prev').css({visibility: 'hidden'});
					} else {
						this.picker.find('.prev').css({visibility: 'visible'});
					}
					if (this.endDate !== Infinity && year >= this.endDate.getUTCFullYear()
						&& month >= this.endDate.getUTCMonth()
						&& day >= this.endDate.getUTCDate()) {
						this.picker.find('.next').css({visibility: 'hidden'});
					} else {
						this.picker.find('.next').css({visibility: 'visible'});
					}
					break;
				case 2:
					if (this.startDate !== -Infinity && year <= this.startDate.getUTCFullYear()
						&& month <= this.startDate.getUTCMonth()) {
						this.picker.find('.prev').css({visibility: 'hidden'});
					} else {
						this.picker.find('.prev').css({visibility: 'visible'});
					}
					if (this.endDate !== Infinity && year >= this.endDate.getUTCFullYear()
						&& month >= this.endDate.getUTCMonth()) {
						this.picker.find('.next').css({visibility: 'hidden'});
					} else {
						this.picker.find('.next').css({visibility: 'visible'});
					}
					break;
				case 3:
				case 4:
					if (this.startDate !== -Infinity && year <= this.startDate.getUTCFullYear()) {
						this.picker.find('.prev').css({visibility: 'hidden'});
					} else {
						this.picker.find('.prev').css({visibility: 'visible'});
					}
					if (this.endDate !== Infinity && year >= this.endDate.getUTCFullYear()) {
						this.picker.find('.next').css({visibility: 'hidden'});
					} else {
						this.picker.find('.next').css({visibility: 'visible'});
					}
					break;
			}
		},
		mousewheel: function (e) {
			e.preventDefault();
			e.stopPropagation();
			if (this.wheelPause) {
				return;
			}
			this.wheelPause = true;
			var originalEvent = e.originalEvent;
			var delta = originalEvent.wheelDelta;
			var mode = delta > 0 ? 1 : (delta === 0) ? 0 : -1;
			if (this.wheelViewModeNavigationInverseDirection) {
				mode = -mode;
			}
			this.showMode(mode);
			setTimeout($.proxy(function () {
				this.wheelPause = false
			}, this), this.wheelViewModeNavigationDelay);
		},
		click: function (e) {
			e.stopPropagation();
			e.preventDefault();
			var target = $(e.target).closest('span, td, th, legend');
			if (target.is('.' + this.icontype)) {
				target = $(target).parent().closest('span, td, th, legend');
			}
			if (target.length == 1) {
				if (target.is('.disabled')) {
					this.element.trigger({
						type:      'outOfRange',
						date:      this.viewDate,
						startDate: this.startDate,
						endDate:   this.endDate
					});
					return;
				}
				switch (target[0].nodeName.toLowerCase()) {
					case 'th':
						switch (target[0].className) {
							case 'switch':
								this.showMode(1);
								break;
							case 'prev':
							case 'next':
								var dir = DPGlobal.modes[this.viewMode].navStep * (target[0].className == 'prev' ? -1 : 1);
								switch (this.viewMode) {
									case 0:
										this.viewDate = this.moveHour(this.viewDate, dir);
										break;
									case 1:
										this.viewDate = this.moveDate(this.viewDate, dir);
										break;
									case 2:
										this.viewDate = this.moveMonth(this.viewDate, dir);
										break;
									case 3:
									case 4:
										this.viewDate = this.moveYear(this.viewDate, dir);
										break;
								}
								this.fill();
								this.element.trigger({
									type:      target[0].className + ':' + this.convertViewModeText(this.viewMode),
									date:      this.viewDate,
									startDate: this.startDate,
									endDate:   this.endDate
								});
								break;
							case 'today':
								var date = new Date();
								date = UTCDate(date.getFullYear(), date.getMonth(), date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds(), 0);
								// Respect startDate and endDate.
								if (date < this.startDate) date = this.startDate;
								else if (date > this.endDate) date = this.endDate;

								this.viewMode = this.startViewMode;
								this.showMode(0);
								this._setDate(date);
								this.fill();
								if (this.autoclose) {
									this.hide();
								}
								break;
						}
						break;
					case 'span':
						if (!target.is('.disabled')) {
							var year = this.viewDate.getUTCFullYear(),
								month = this.viewDate.getUTCMonth(),
								day = this.viewDate.getUTCDate(),
								hours = this.viewDate.getUTCHours(),
								minutes = this.viewDate.getUTCMinutes(),
								seconds = this.viewDate.getUTCSeconds();

							if (target.is('.month')) {
								this.viewDate.setUTCDate(1);
								month = target.parent().find('span').index(target);
								day = this.viewDate.getUTCDate();
								this.viewDate.setUTCMonth(month);
								this.element.trigger({
									type: 'changeMonth',
									date: this.viewDate
								});
								if (this.viewSelect >= 3) {
									this._setDate(UTCDate(year, month, day, hours, minutes, seconds, 0));
								}
							} else if (target.is('.year')) {
								this.viewDate.setUTCDate(1);
								year = parseInt(target.text(), 10) || 0;
								this.viewDate.setUTCFullYear(year);
								this.element.trigger({
									type: 'changeYear',
									date: this.viewDate
								});
								if (this.viewSelect >= 4) {
									this._setDate(UTCDate(year, month, day, hours, minutes, seconds, 0));
								}
							} else if (target.is('.hour')) {
								hours = parseInt(target.text(), 10) || 0;
								if (target.hasClass('hour_am') || target.hasClass('hour_pm')) {
									if (hours == 12 && target.hasClass('hour_am')) {
										hours = 0;
									} else if (hours != 12 && target.hasClass('hour_pm')) {
										hours += 12;
									}
								}
								this.viewDate.setUTCHours(hours);
								this.element.trigger({
									type: 'changeHour',
									date: this.viewDate
								});
								if (this.viewSelect >= 1) {
									this._setDate(UTCDate(year, month, day, hours, minutes, seconds, 0));
								}
							} else if (target.is('.minute')) {
								minutes = parseInt(target.text().substr(target.text().indexOf(':') + 1), 10) || 0;
								this.viewDate.setUTCMinutes(minutes);
								this.element.trigger({
									type: 'changeMinute',
									date: this.viewDate
								});
								if (this.viewSelect >= 0) {
									this._setDate(UTCDate(year, month, day, hours, minutes, seconds, 0));
								}
							}
							if (this.viewMode != 0) {
								var oldViewMode = this.viewMode;
								this.showMode(-1);
								this.fill();
								if (oldViewMode == this.viewMode && this.autoclose) {
									this.hide();
								}
							} else {
								this.fill();
								if (this.autoclose) {
									this.hide();
								}
							}
						}
						break;
					case 'td':
						if (target.is('.day') && !target.is('.disabled')) {
							var day = parseInt(target.text(), 10) || 1;
							var year = this.viewDate.getUTCFullYear(),
								month = this.viewDate.getUTCMonth(),
								hours = this.viewDate.getUTCHours(),
								minutes = this.viewDate.getUTCMinutes(),
								seconds = this.viewDate.getUTCSeconds();
							if (target.is('.old')) {
								if (month === 0) {
									month = 11;
									year -= 1;
								} else {
									month -= 1;
								}
							} else if (target.is('.new')) {
								if (month == 11) {
									month = 0;
									year += 1;
								} else {
									month += 1;
								}
							}
							this.viewDate.setUTCFullYear(year);
							this.viewDate.setUTCMonth(month, day);
							this.element.trigger({
								type: 'changeDay',
								date: this.viewDate
							});
							if (this.viewSelect >= 2) {
								this._setDate(UTCDate(year, month, day, hours, minutes, seconds, 0));
							}
						}
						var oldViewMode = this.viewMode;
						this.showMode(-1);
						this.fill();
						if (oldViewMode == this.viewMode && this.autoclose) {
							this.hide();
						}
						break;
				}
			}
		},
		_setDate: function (date, which) {
			if (!which || which == 'date')
				this.date = date;
			if (!which || which == 'view')
				this.viewDate = date;
			this.fill();
			this.setValue();
			var element;
			if (this.isInput) {
				element = this.element;
			} else if (this.component) {
				element = this.element.find('input');
			}
			if (element) {
				element.change();
				if (this.autoclose && (!which || which == 'date')) {
					//this.hide();
				}
			}
			this.element.trigger({
				type: 'changeDate',
				date: this.date
			});
		},
		moveMinute: function (date, dir) {
			if (!dir) return date;
			var new_date = new Date(date.valueOf());
			//dir = dir > 0 ? 1 : -1;
			new_date.setUTCMinutes(new_date.getUTCMinutes() + (dir * this.minuteStep));
			return new_date;
		},
		moveHour: function (date, dir) {
			if (!dir) return date;
			var new_date = new Date(date.valueOf());
			//dir = dir > 0 ? 1 : -1;
			new_date.setUTCHours(new_date.getUTCHours() + dir);
			return new_date;
		},
		moveDate: function (date, dir) {
			if (!dir) return date;
			var new_date = new Date(date.valueOf());
			//dir = dir > 0 ? 1 : -1;
			new_date.setUTCDate(new_date.getUTCDate() + dir);
			return new_date;
		},
		moveMonth: function (date, dir) {
			if (!dir) return date;
			var new_date = new Date(date.valueOf()),
				day = new_date.getUTCDate(),
				month = new_date.getUTCMonth(),
				mag = Math.abs(dir),
				new_month, test;
			dir = dir > 0 ? 1 : -1;
			if (mag == 1) {
				test = dir == -1
					// If going back one month, make sure month is not current month
					// (eg, Mar 31 -> Feb 31 == Feb 28, not Mar 02)
					? function () {
					return new_date.getUTCMonth() == month;
				}
					// If going forward one month, make sure month is as expected
					// (eg, Jan 31 -> Feb 31 == Feb 28, not Mar 02)
					: function () {
					return new_date.getUTCMonth() != new_month;
				};
				new_month = month + dir;
				new_date.setUTCMonth(new_month);
				// Dec -> Jan (12) or Jan -> Dec (-1) -- limit expected date to 0-11
				if (new_month < 0 || new_month > 11)
					new_month = (new_month + 12) % 12;
			} else {
				// For magnitudes >1, move one month at a time...
				for (var i = 0; i < mag; i++)
					// ...which might decrease the day (eg, Jan 31 to Feb 28, etc)...
					new_date = this.moveMonth(new_date, dir);
				// ...then reset the day, keeping it in the new month
				new_month = new_date.getUTCMonth();
				new_date.setUTCDate(day);
				test = function () {
					return new_month != new_date.getUTCMonth();
				};
			}
			// Common date-resetting loop -- if date is beyond end of month, make it
			// end of month
			while (test()) {
				new_date.setUTCDate(--day);
				new_date.setUTCMonth(new_month);
			}
			return new_date;
		},
		moveYear: function (date, dir) {
			return this.moveMonth(date, dir * 12);
		},
		dateWithinRange: function (date) {
			return date >= this.startDate && date <= this.endDate;
		},
		keydown: function (e) {
			if (this.picker.is(':not(:visible)')) {
				if (e.keyCode == 27) // allow escape to hide and re-show picker
					this.show();
				return;
			}
			var dateChanged = false,
				dir, day, month,
				newDate, newViewDate;
			switch (e.keyCode) {
				case 27: // escape
					this.hide();
					e.preventDefault();
					break;
				case 37: // left
				case 39: // right
					if (!this.keyboardNavigation) break;
					dir = e.keyCode == 37 ? -1 : 1;
					viewMode = this.viewMode;
					if (e.ctrlKey) {
						viewMode += 2;
					} else if (e.shiftKey) {
						viewMode += 1;
					}
					if (viewMode == 4) {
						newDate = this.moveYear(this.date, dir);
						newViewDate = this.moveYear(this.viewDate, dir);
					} else if (viewMode == 3) {
						newDate = this.moveMonth(this.date, dir);
						newViewDate = this.moveMonth(this.viewDate, dir);
					} else if (viewMode == 2) {
						newDate = this.moveDate(this.date, dir);
						newViewDate = this.moveDate(this.viewDate, dir);
					} else if (viewMode == 1) {
						newDate = this.moveHour(this.date, dir);
						newViewDate = this.moveHour(this.viewDate, dir);
					} else if (viewMode == 0) {
						newDate = this.moveMinute(this.date, dir);
						newViewDate = this.moveMinute(this.viewDate, dir);
					}
					if (this.dateWithinRange(newDate)) {
						this.date = newDate;
						this.viewDate = newViewDate;
						this.setValue();
						this.update();
						e.preventDefault();
						dateChanged = true;
					}
					break;
				case 38: // up
				case 40: // down
					if (!this.keyboardNavigation) break;
					dir = e.keyCode == 38 ? -1 : 1;
					viewMode = this.viewMode;
					if (e.ctrlKey) {
						viewMode += 2;
					} else if (e.shiftKey) {
						viewMode += 1;
					}
					if (viewMode == 4) {
						newDate = this.moveYear(this.date, dir);
						newViewDate = this.moveYear(this.viewDate, dir);
					} else if (viewMode == 3) {
						newDate = this.moveMonth(this.date, dir);
						newViewDate = this.moveMonth(this.viewDate, dir);
					} else if (viewMode == 2) {
						newDate = this.moveDate(this.date, dir * 7);
						newViewDate = this.moveDate(this.viewDate, dir * 7);
					} else if (viewMode == 1) {
						if (this.showMeridian) {
							newDate = this.moveHour(this.date, dir * 6);
							newViewDate = this.moveHour(this.viewDate, dir * 6);
						} else {
							newDate = this.moveHour(this.date, dir * 4);
							newViewDate = this.moveHour(this.viewDate, dir * 4);
						}
					} else if (viewMode == 0) {
						newDate = this.moveMinute(this.date, dir * 4);
						newViewDate = this.moveMinute(this.viewDate, dir * 4);
					}
					if (this.dateWithinRange(newDate)) {
						this.date = newDate;
						this.viewDate = newViewDate;
						this.setValue();
						this.update();
						e.preventDefault();
						dateChanged = true;
					}
					break;
				case 13: // enter
					if (this.viewMode != 0) {
						var oldViewMode = this.viewMode;
						this.showMode(-1);
						this.fill();
						if (oldViewMode == this.viewMode && this.autoclose) {
							this.hide();
						}
					} else {
						this.fill();
						if (this.autoclose) {
							this.hide();
						}
					}
					e.preventDefault();
					break;
				case 9: // tab
					this.hide();
					break;
			}
			if (dateChanged) {
				var element;
				if (this.isInput) {
					element = this.element;
				} else if (this.component) {
					element = this.element.find('input');
				}
				if (element) {
					element.change();
				}
				this.element.trigger({
					type: 'changeDate',
					date: this.date
				});
			}
		},
		showMode: function (dir) {
			if (dir) {
				var newViewMode = Math.max(0, Math.min(DPGlobal.modes.length - 1, this.viewMode + dir));
				if (newViewMode >= this.minView && newViewMode <= this.maxView) {
					this.element.trigger({
						type:        'changeMode',
						date:        this.viewDate,
						oldViewMode: this.viewMode,
						newViewMode: newViewMode
					});

					this.viewMode = newViewMode;
				}
			}
			/*
			 vitalets: fixing bug of very special conditions:
			 jquery 1.7.1 + webkit + show inline datetimepicker in bootstrap popover.
			 Method show() does not set display css correctly and datetimepicker is not shown.
			 Changed to .css('display', 'block') solve the problem.
			 See https://github.com/vitalets/x-editable/issues/37

			 In jquery 1.7.2+ everything works fine.
			 */
			//this.picker.find('>div').hide().filter('.datetimepicker-'+DPGlobal.modes[this.viewMode].clsName).show();
			this.picker.find('>div').hide().filter('.datetimepicker-' + DPGlobal.modes[this.viewMode].clsName).css('display', 'block');
			this.updateNavArrows();
		},
		reset: function (e) {
			this._setDate(null, 'date');
		},
		convertViewModeText:  function (viewMode) {
			switch (viewMode) {
				case 4:
					return 'decade';
				case 3:
					return 'year';
				case 2:
					return 'month';
				case 1:
					return 'day';
				case 0:
					return 'hour';
			}
		}
	};
	var old = $.fn.datetimepicker;
	$.fn.datetimepicker = function (option) {
		var args = Array.apply(null, arguments);
		args.shift();
		var internal_return;
		this.each(function () {
			var $this = $(this),
				data = $this.data('datetimepicker'),
				options = typeof option == 'object' && option;
			if (!data) {
				$this.data('datetimepicker', (data = new Datetimepicker(this, $.extend({}, $.fn.datetimepicker.defaults, options))));
			}
			if (typeof option == 'string' && typeof data[option] == 'function') {
				internal_return = data[option].apply(data, args);
				if (internal_return !== undefined) {
					return false;
				}
			}
		});
		if (internal_return !== undefined)
			return internal_return;
		else
			return this;
	};
	$.fn.datetimepicker.defaults = {
	};
	$.fn.datetimepicker.Constructor = Datetimepicker;
	var dates = $.fn.datetimepicker.dates = {
		en: {
			days:        ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
			daysShort:   ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
			daysMin:     ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"],
			months:      ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
			monthsShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
			meridiem:    ["am", "pm"],
			suffix:      ["st", "nd", "rd", "th"],
			today:       "Today"
		}
	};
	var DPGlobal = {
		modes:            [
			{
				clsName: 'minutes',
				navFnc:  'Hours',
				navStep: 1
			},
			{
				clsName: 'hours',
				navFnc:  'Date',
				navStep: 1
			},
			{
				clsName: 'days',
				navFnc:  'Month',
				navStep: 1
			},
			{
				clsName: 'months',
				navFnc:  'FullYear',
				navStep: 1
			},
			{
				clsName: 'years',
				navFnc:  'FullYear',
				navStep: 10
			}
		],
		isLeapYear:       function (year) {
			return (((year % 4 === 0) && (year % 100 !== 0)) || (year % 400 === 0))
		},
		getDaysInMonth:   function (year, month) {
			return [31, (DPGlobal.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month]
		},
		getDefaultFormat: function (type, field) {
			if (type == "standard") {
				if (field == 'input')
					return 'yyyy-mm-dd hh:ii';
				else
					return 'yyyy-mm-dd hh:ii:ss';
			} else if (type == "php") {
				if (field == 'input')
					return 'Y-m-d H:i';
				else
					return 'Y-m-d H:i:s';
			} else {
				throw new Error("Invalid format type.");
			}
		},
		validParts:       function (type) {
			if (type == "standard") {
				return /hh?|HH?|p|P|ii?|ss?|dd?|DD?|mm?|MM?|yy(?:yy)?/g;
			} else if (type == "php") {
				return /[dDjlNwzFmMnStyYaABgGhHis]/g;
			} else {
				throw new Error("Invalid format type.");
			}
		},
		nonpunctuation:   /[^ -\/:-@\[-`{-~\t\n\rTZ]+/g,
		parseFormat:      function (format, type) {
			// IE treats \0 as a string end in inputs (truncating the value),
			// so it's a bad format delimiter, anyway
			var separators = format.replace(this.validParts(type), '\0').split('\0'),
				parts = format.match(this.validParts(type));
			if (!separators || !separators.length || !parts || parts.length == 0) {
				throw new Error("Invalid date format.");
			}
			return {separators: separators, parts: parts};
		},
		parseDate:        function (date, format, language, type) {
			if (date instanceof Date) {
				var dateUTC = new Date(date.valueOf() - date.getTimezoneOffset() * 60000);
				dateUTC.setMilliseconds(0);
				return dateUTC;
			}
			if (/^\d{4}\-\d{1,2}\-\d{1,2}$/.test(date)) {
				format = this.parseFormat('yyyy-mm-dd', type);
			}
			if (/^\d{4}\-\d{1,2}\-\d{1,2}[T ]\d{1,2}\:\d{1,2}$/.test(date)) {
				format = this.parseFormat('yyyy-mm-dd hh:ii', type);
			}
			if (/^\d{4}\-\d{1,2}\-\d{1,2}[T ]\d{1,2}\:\d{1,2}\:\d{1,2}[Z]{0,1}$/.test(date)) {
				format = this.parseFormat('yyyy-mm-dd hh:ii:ss', type);
			}
			if (/^[-+]\d+[dmwy]([\s,]+[-+]\d+[dmwy])*$/.test(date)) {
				var part_re = /([-+]\d+)([dmwy])/,
					parts = date.match(/([-+]\d+)([dmwy])/g),
					part, dir;
				date = new Date();
				for (var i = 0; i < parts.length; i++) {
					part = part_re.exec(parts[i]);
					dir = parseInt(part[1]);
					switch (part[2]) {
						case 'd':
							date.setUTCDate(date.getUTCDate() + dir);
							break;
						case 'm':
							date = Datetimepicker.prototype.moveMonth.call(Datetimepicker.prototype, date, dir);
							break;
						case 'w':
							date.setUTCDate(date.getUTCDate() + dir * 7);
							break;
						case 'y':
							date = Datetimepicker.prototype.moveYear.call(Datetimepicker.prototype, date, dir);
							break;
					}
				}
				return UTCDate(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDate(), date.getUTCHours(), date.getUTCMinutes(), date.getUTCSeconds(), 0);
			}
			var parts = date && date.toString().match(this.nonpunctuation) || [],
				date = new Date(0, 0, 0, 0, 0, 0, 0),
				parsed = {},
				setters_order = ['hh', 'h', 'ii', 'i', 'ss', 's', 'yyyy', 'yy', 'M', 'MM', 'm', 'mm', 'D', 'DD', 'd', 'dd', 'H', 'HH', 'p', 'P'],
				setters_map = {
					hh:   function (d, v) {
						return d.setUTCHours(v);
					},
					h:    function (d, v) {
						return d.setUTCHours(v);
					},
					HH:   function (d, v) {
						return d.setUTCHours(v == 12 ? 0 : v);
					},
					H:    function (d, v) {
						return d.setUTCHours(v == 12 ? 0 : v);
					},
					ii:   function (d, v) {
						return d.setUTCMinutes(v);
					},
					i:    function (d, v) {
						return d.setUTCMinutes(v);
					},
					ss:   function (d, v) {
						return d.setUTCSeconds(v);
					},
					s:    function (d, v) {
						return d.setUTCSeconds(v);
					},
					yyyy: function (d, v) {
						return d.setUTCFullYear(v);
					},
					yy:   function (d, v) {
						return d.setUTCFullYear(2000 + v);
					},
					m:    function (d, v) {
						v -= 1;
						while (v < 0) v += 12;
						v %= 12;
						d.setUTCMonth(v);
						while (d.getUTCMonth() != v)
							if (isNaN(d.getUTCMonth()))
								return d;
							else
								d.setUTCDate(d.getUTCDate() - 1);
						return d;
					},
					d:    function (d, v) {
						return d.setUTCDate(v);
					},
					p:    function (d, v) {
						return d.setUTCHours(v == 1 ? d.getUTCHours() + 12 : d.getUTCHours());
					}
				},
				val, filtered, part;
			setters_map['M'] = setters_map['MM'] = setters_map['mm'] = setters_map['m'];
			setters_map['dd'] = setters_map['d'];
			setters_map['P'] = setters_map['p'];
			date = UTCDate(date.getFullYear(), date.getMonth(), date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds());
			if (parts.length == format.parts.length) {
				for (var i = 0, cnt = format.parts.length; i < cnt; i++) {
					val = parseInt(parts[i], 10);
					part = format.parts[i];
					if (isNaN(val)) {
						switch (part) {
							case 'MM':
								filtered = $(dates[language].months).filter(function () {
									var m = this.slice(0, parts[i].length),
										p = parts[i].slice(0, m.length);
									return m == p;
								});
								val = $.inArray(filtered[0], dates[language].months) + 1;
								break;
							case 'M':
								filtered = $(dates[language].monthsShort).filter(function () {
									var m = this.slice(0, parts[i].length),
										p = parts[i].slice(0, m.length);
									return m.toLowerCase() == p.toLowerCase();
								});
								val = $.inArray(filtered[0], dates[language].monthsShort) + 1;
								break;
							case 'p':
							case 'P':
								val = $.inArray(parts[i].toLowerCase(), dates[language].meridiem);
								break;
						}
					}
					parsed[part] = val;
				}
				for (var i = 0, s; i < setters_order.length; i++) {
					s = setters_order[i];
					if (s in parsed && !isNaN(parsed[s]))
						setters_map[s](date, parsed[s])
				}
			}
			return date;
		},
		formatDate:       function (date, format, language, type) {
			if (date == null) {
				return '';
			}
			var val;
			if (type == 'standard') {
				val = {
					// year
					yy:   date.getUTCFullYear().toString().substring(2),
					yyyy: date.getUTCFullYear(),
					// month
					m:    date.getUTCMonth() + 1,
					M:    dates[language].monthsShort[date.getUTCMonth()],
					MM:   dates[language].months[date.getUTCMonth()],
					// day
					d:    date.getUTCDate(),
					D:    dates[language].daysShort[date.getUTCDay()],
					DD:   dates[language].days[date.getUTCDay()],
					p:    (dates[language].meridiem.length == 2 ? dates[language].meridiem[date.getUTCHours() < 12 ? 0 : 1] : ''),
					// hour
					h:    date.getUTCHours(),
					// minute
					i:    date.getUTCMinutes(),
					// second
					s:    date.getUTCSeconds()
				};

				if (dates[language].meridiem.length == 2) {
					val.H = (val.h % 12 == 0 ? 12 : val.h % 12);
				}
				else {
					val.H = val.h;
				}
				val.HH = (val.H < 10 ? '0' : '') + val.H;
				val.P = val.p.toUpperCase();
				val.hh = (val.h < 10 ? '0' : '') + val.h;
				val.ii = (val.i < 10 ? '0' : '') + val.i;
				val.ss = (val.s < 10 ? '0' : '') + val.s;
				val.dd = (val.d < 10 ? '0' : '') + val.d;
				val.mm = (val.m < 10 ? '0' : '') + val.m;
			} else if (type == 'php') {
				// php format
				val = {
					// year
					y: date.getUTCFullYear().toString().substring(2),
					Y: date.getUTCFullYear(),
					// month
					F: dates[language].months[date.getUTCMonth()],
					M: dates[language].monthsShort[date.getUTCMonth()],
					n: date.getUTCMonth() + 1,
					t: DPGlobal.getDaysInMonth(date.getUTCFullYear(), date.getUTCMonth()),
					// day
					j: date.getUTCDate(),
					l: dates[language].days[date.getUTCDay()],
					D: dates[language].daysShort[date.getUTCDay()],
					w: date.getUTCDay(), // 0 -> 6
					N: (date.getUTCDay() == 0 ? 7 : date.getUTCDay()),       // 1 -> 7
					S: (date.getUTCDate() % 10 <= dates[language].suffix.length ? dates[language].suffix[date.getUTCDate() % 10 - 1] : ''),
					// hour
					a: (dates[language].meridiem.length == 2 ? dates[language].meridiem[date.getUTCHours() < 12 ? 0 : 1] : ''),
					g: (date.getUTCHours() % 12 == 0 ? 12 : date.getUTCHours() % 12),
					G: date.getUTCHours(),
					// minute
					i: date.getUTCMinutes(),
					// second
					s: date.getUTCSeconds()
				};
				val.m = (val.n < 10 ? '0' : '') + val.n;
				val.d = (val.j < 10 ? '0' : '') + val.j;
				val.A = val.a.toString().toUpperCase();
				val.h = (val.g < 10 ? '0' : '') + val.g;
				val.H = (val.G < 10 ? '0' : '') + val.G;
				val.i = (val.i < 10 ? '0' : '') + val.i;
				val.s = (val.s < 10 ? '0' : '') + val.s;
			} else {
				throw new Error("Invalid format type.");
			}
			var date = [],
				seps = $.extend([], format.separators);
			for (var i = 0, cnt = format.parts.length; i < cnt; i++) {
				if (seps.length) {
					date.push(seps.shift());
				}
				date.push(val[format.parts[i]]);
			}
			if (seps.length) {
				date.push(seps.shift());
			}
			return date.join('');
		},
		convertViewMode:  function (viewMode) {
			switch (viewMode) {
				case 4:
				case 'decade':
					viewMode = 4;
					break;
				case 3:
				case 'year':
					viewMode = 3;
					break;
				case 2:
				case 'month':
					viewMode = 2;
					break;
				case 1:
				case 'day':
					viewMode = 1;
					break;
				case 0:
				case 'hour':
					viewMode = 0;
					break;
			}

			return viewMode;
		},
		headTemplate:     '<thead>' +
							  '<tr>' +
							  '<th class="prev"><i class="{leftArrow}"/></th>' +
							  '<th colspan="5" class="switch"></th>' +
							  '<th class="next"><i class="{rightArrow}"/></th>' +
							  '</tr>' +
			'</thead>',
		headTemplateV3:   '<thead>' +
							  '<tr>' +
							  '<th class="prev"><span class="{iconType} {leftArrow}"></span> </th>' +
							  '<th colspan="5" class="switch"></th>' +
							  '<th class="next"><span class="{iconType} {rightArrow}"></span> </th>' +
							  '</tr>' +
			'</thead>',
		contTemplate:     '<tbody><tr><td colspan="7"></td></tr></tbody>',
		footTemplate:     '<tfoot><tr><th colspan="7" class="today"></th></tr></tfoot>'
	};
	DPGlobal.template = '<div class="datetimepicker">' +
		'<div class="datetimepicker-minutes">' +
		'<table class=" table-condensed">' +
		DPGlobal.headTemplate +
		DPGlobal.contTemplate +
		DPGlobal.footTemplate +
		'</table>' +
		'</div>' +
		'<div class="datetimepicker-hours">' +
		'<table class=" table-condensed">' +
		DPGlobal.headTemplate +
		DPGlobal.contTemplate +
		DPGlobal.footTemplate +
		'</table>' +
		'</div>' +
		'<div class="datetimepicker-days">' +
		'<table class=" table-condensed">' +
		DPGlobal.headTemplate +
		'<tbody></tbody>' +
		DPGlobal.footTemplate +
		'</table>' +
		'</div>' +
		'<div class="datetimepicker-months">' +
		'<table class="table-condensed">' +
		DPGlobal.headTemplate +
		DPGlobal.contTemplate +
		DPGlobal.footTemplate +
		'</table>' +
		'</div>' +
		'<div class="datetimepicker-years">' +
		'<table class="table-condensed">' +
		DPGlobal.headTemplate +
		DPGlobal.contTemplate +
		DPGlobal.footTemplate +
		'</table>' +
		'</div>' +
		'</div>';
	DPGlobal.templateV3 = '<div class="datetimepicker">' +
		'<div class="datetimepicker-minutes">' +
		'<table class=" table-condensed">' +
		DPGlobal.headTemplateV3 +
		DPGlobal.contTemplate +
		DPGlobal.footTemplate +
		'</table>' +
		'</div>' +
		'<div class="datetimepicker-hours">' +
		'<table class=" table-condensed">' +
		DPGlobal.headTemplateV3 +
		DPGlobal.contTemplate +
		DPGlobal.footTemplate +
		'</table>' +
		'</div>' +
		'<div class="datetimepicker-days">' +
		'<table class=" table-condensed">' +
		DPGlobal.headTemplateV3 +
		'<tbody></tbody>' +
		DPGlobal.footTemplate +
		'</table>' +
		'</div>' +
		'<div class="datetimepicker-months">' +
		'<table class="table-condensed">' +
		DPGlobal.headTemplateV3 +
		DPGlobal.contTemplate +
		DPGlobal.footTemplate +
		'</table>' +
		'</div>' +
		'<div class="datetimepicker-years">' +
		'<table class="table-condensed">' +
		DPGlobal.headTemplateV3 +
		DPGlobal.contTemplate +
		DPGlobal.footTemplate +
		'</table>' +
		'</div>' +
		'</div>';
	$.fn.datetimepicker.DPGlobal = DPGlobal;
	/* DATETIMEPICKER NO CONFLICT */
	$.fn.datetimepicker.noConflict = function () {
		$.fn.datetimepicker = old;
		return this;
	};
	/* DATETIMEPICKER DATA-API */
	$(document).on(
		'focus.datetimepicker.data-api click.datetimepicker.data-api',
		'[data-provide="datetimepicker"]',
		function (e) {
			var $this = $(this);
			if ($this.data('datetimepicker')) return;
			e.preventDefault();
			// component click requires us to explicitly show it
			$this.datetimepicker('show');
		}
	);
	$(function () {
		$('[data-provide="datetimepicker-inline"]').datetimepicker();
	});
	/* datetimepicker END */
	
	
	
	
	
	
	/* iTreeselect BEGIN */ /* 树形菜单，可无限增加节点，含有自动筛选功能，对每个元素有 增 删 改 功能 */
	/* 参数
	 * 是否创建主节点		isparent
	 * 带复选框			ischeckbox
	 * 查询定位			isselect
	 * 增加节点			isinsert
	 * 修改节点			isedit
	 * 删除节点			isdelete
	 * ajax接口方法前缀	isajax	每一级向后加 1 ，ajax方法名以此类推，所以自己必须知道一共有多少个节点 ， 后台需要按照创建这些ajax的访问方法
	 * 从后台读取的数据	treenodearr		数组分为两部分 各占一半 一部分为节点名 第二部分为 是否存在子节点， 1 为存在
	 * 自定义函数			freestyle
	 * 共有多少子级节点	treecount     由于ajax不能反回参数，所以必须事先声明有多少级ul，创建时逐级为他们添加识别类，主节点为表示类为 node_0 ， 相应的，ajax请求方法个数为 treecount -1
	 */
	var _iTreeselect = 'iTreeselect';
	$.fn[ _iTreeselect ] = function ( options , treenodearr , freestyle ) {
		var stack = $(),
			worker = function( object ) {
				object.each( function () {
					stack = stack.add( $( this ) );
				})
			};
		if ( typeof options == 'object' || !options ) {
			var settings = $.extend({
					isparent : false, 		/* 支持创建主节点 */
					ischeckbox : false,		/* 带复选框 */
					isselect : false,		/* 查询定位 */
					isinsert : false,		/* 增加节点 */
					isedit : false,			/* 修改节点 */
					isdelete : false,		/* 删除节点 */
					isajax : '',				/* ajax方法前缀 */
					treecount : 0			/* tree共有多少子级节点 */
				} , options ),
				_isparent = settings.isparent,
				_ischeckbox = settings.ischeckbox,
				_isselect = settings.isselect,
				_isinsert = settings.isinsert,
				_isedit = settings.isedit,
				_isdelete = settings.isdelete,
				_isajax = settings.isajax,
				_treecount = settings.treecount;
			worker( this );
			/* 主方法里只创建主节点，主节点只有一个，当点击主节点时才会调用 ajax 继续创建子节点 */
			return stack.each( function () {
				var self = $( this ),
					tree_container_first = '<div class="tree_container">',
					tree_container_last = '</div>',
					tree_nav_first = '<ul class="tree_nav node_0">',
					tree_nav_last = '</ul>',
					_nodes = new Array(), /* 壮哉主节点的数组 */
					master_node = '',
					_checkbox = '',
					_searchfn = '',
					_insertbtn = '',
					_editbtn = '',
					_deletebtn = '',
					_k = 0;
				if ( _isparent ) {
					master_node = '';
				}
				if ( _ischeckbox ) {
					_checkbox = '';
				}
				if ( _isselect ) {
					_searchfn = '';
				}
				if ( _isinsert ) {
					_insertbtn = '';
				}
				if ( _isedit ) {
					_editbtn = '';
				}
				if ( _isdelete ) {
					_deletebtn = '';
				}
				if ( treenodearr.length > 0 ) {
					_nodes = build_tree_elem ( treenodearr );
				}
				/* 创建主节点树 */
				self.append( tree_container_first + tree_nav_first + _nodes.join( '' ) + tree_nav_last + tree_container_last );
				/* 点击主节点时调用ajax */
				isnode ( _isajax + _k , _k , _treecount , _isajax );
			});
		}
	}
	/* 创建li元素 */
	function build_tree_elem ( treenodearr ) {
		var node_name = treenodearr.splice( 0 , treenodearr.length/2 ),
			node_identify = treenodearr,
			_nodes = new Array();
		for ( var c = 0 ; c < node_name.length ; c++ ) {
			var _icon = '';
			if( node_identify[ c ] == 1 ){
				_icon = '<i class="icon-plus"></i>';
			}
			_nodes[ c ] = '<li class="tree_node"><a><span class="node_icon">' + _icon + '</span><span class="node_dis">' + node_name[ c ] + '</span></a></li>'; 
		}
		return _nodes;
	}
	/* 判断此节点下边是否存在子节点 */
	function isnode ( _isajax , _k , _treecount , _isajaxstr ) {
		$( '.tree_node .node_icon' ).on( 'click' , function () {
			/* 如果被点击元素的存在子级元素，则向下进行 */
			if ( $(this).children().length > 0 ) {
				/* 如果此被点击元素的li父级元素中含有ul则移除 */
				if ( $( this ).parent().parent().find( '.tree_nav' ).length > 0 ){
					$( this ).parent().parent().find( '.tree_nav' ).remove();
				} else if ( _treecount > 0 && ( _k + 1 ) < _treecount ) {
					eval( _isajax )( $( this ) , _k , _treecount , _isajaxstr );
				}
			}
			preventdefault
		});
	}
	/* 加载子节点信息 */
	function build_nodetree ( node_arr , obj , _k , _treecount , _isajax ) {
		if ( node_arr.length > 0 ) {
			var _nodes = build_tree_elem ( node_arr );
			obj.parent().parent().append( '<ul class="tree_nav node_' + _k + 1 + '">' + _nodes.join( '' ) + '</ul>' );
			isnode ( _isajax + parseInt( _k + 1 ) , _k + 1 , _treecount , _isajax );
		}
	}
	/* iTreeselect END */
	
	
	
	
	
	
	/* 开始调用 BEGIN */	
	
	/* 复选、单选 */
	$( 'input' ).iCheck();
	
	/* 自动筛选下拉菜单 */
	$( '.dropdown.intelligent' ).iSelect({
		intelligent : true,
	    ajaxURL : '',
	    jsonstr : '',
	    jsonname : ''
	});
	
	/* 自动筛选+复选框下拉菜单 */
	$( '.dropdown.intelligent_checkbox' ).iSelect({
		intelligent : true,
	    checkbox : true,
	    ajaxURL : '',
	    jsonstr : '',
	    jsonname : ''
	});
												  
	/* 带表格的下拉菜单 */
	$( '.dropdown.filterandcheck' ).iSelect({
		filterandcheck : true,
	 	ajaxURL : '',
	 	jsonstr : '',
	 	jsonname : ''
	});
	
	/* 表格 */
	$( '.tooltip-demo_table' ).iBuildtable({  
		table_checkbox : true,
		isinsert : true,
		isdelete : true,
		isupdate : true,
		issearch : true,
		isthsort : true,
		dragth : true,
		isinsidetdsort : true,
		pagination : true,
		isdoubleclick : false,
		isrolenumber : 3,
		isdiscription : false,
		isrefresh : false,
		isremove : false,
		issave : true,
		ajaxURL : '',
		jsonstr : '',
		jsonname : ''
	});
	
	/* 鼠标悬停提示框 */						
	$( '.bs-docs-tooltip-examples.toollip > li > a' ).tooltip( 'hide' );
	
	/* 鼠标点击提示框 */	
	$( '.bs-docs-tooltip-examples.prop > li > a' ).popover( 'hide' );
	
	/* 设置时间选择器的语言 */
	$.fn.datetimepicker.dates[ 'Chinese' ] = {
		days: ["日", "一", "二", "三", "四", "五", "六", "日"],
		daysShort: ["日", "一", "二", "三", "四", "五", "六", "日"],
		daysMin: ["日", "一", "二", "三", "四", "五", "六", "日"],
		months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		today: "今天",
		meridiem: ["am", "pm"]
	};
	
	/* 时间选择器 */
	$( '.form_datetime' ).datetimepicker({
        language:  'Chinese',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 0,
		todayHighlight: 1,
		startView: 4,
		forceParse: 0,
        showMeridian: 1,
		pickerPosition: "bottom-left"
    });
	
	/* 监听页面滚动的导航 */
	$( '.rightside_navbar' ).iScrollmenu();
	
	
	
	
	
	
	
	
	
	
	
	
	/* 树形菜单 */
	function treetestajax() {
		/* 这里使用ajax从后台读取主节点信息 */
		var arr = ['节点0','节点1','节点2','节点3',1,1,1,0];
		$( '.window_5 .bs-docs-example' ).iTreeselect({ 
			isparent : false,
			ischeckbox : false,
			isselect : false,
			isinsert : false,
			isedit : false,
			isdelete : false,
			isajax : 'treetoservice_',
			treecount : 5
		 }, arr );
	}
	treetestajax();				 
	/* 开始调用 END */
	
	
	
	
	
	/* 测试函数 BEGIN */
	
	/* 树形菜单测试   树形菜单每一节点调用的函数 （这里测试一下5个节点。第一节点永远为0，之后每一级递增为 1） */
	function treetoservice_0 ( obj , _k , _treecount , _isajax ) {
		/* 子级节点 */
		var arr = ['节点0','节点1','节点2','节点3',0,1,1,0];
		build_nodetree( arr , obj , _k , _treecount , _isajax );
	}
	function treetoservice_1 ( obj , _k , _treecount , _isajax ) {
		/* 子级节点 */
		var arr = ['节点0','节点1','节点2','节点3',1,1,1,0];
		build_nodetree( arr , obj , _k , _treecount , _isajax );
	}
	function treetoservice_2 ( obj , _k , _treecount , _isajax ) {
		/* 子级节点 */
		var arr = ['节点0','节点1','节点2','节点3',1,1,1,1];
		build_nodetree( arr , obj , _k , _treecount , _isajax );
	}
	function treetoservice_3 ( obj , _k , _treecount , _isajax ) {
		/* 子级节点 */
		var arr = ['节点0','节点1','节点2','节点3',0,0,0,0];
		build_nodetree( arr , obj , _k , _treecount , _isajax );
	}
	
	/* 测试函数 END */
})(window.jQuery);