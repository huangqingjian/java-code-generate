<!DOCTYPE html>
<html>
<head>
    <title>banner管理</title>
    <#include "/common/head.ftl"/>
</head>
<body class="pear-container">
<div class="layui-card">
    <div class="layui-card-body">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <div class="layui-form-item layui-inline">
                    <label class="layui-form-label">搜索关键字</label>
                    <div class="layui-input-inline">
                        <input type="text" name="q" placeholder="" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item layui-inline">
                    <button class="pear-btn pear-btn-md pear-btn-primary" lay-submit lay-filter="banner-query">
                        <i class="layui-icon layui-icon-search"></i>
                        查询
                    </button>
                    <button type="reset" class="pear-btn pear-btn-md">
                        <i class="layui-icon layui-icon-refresh"></i>
                        重置
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="layui-card">
    <div class="layui-card-body">
        <table id="banner-table" lay-filter="banner-table"></table>
    </div>
</div>

<script type="text/html" id="banner-toolbar">
    <button class="pear-btn pear-btn-primary pear-btn-md" lay-event="add">
        <i class="layui-icon layui-icon-add-1"></i>
        新增
    </button>
</script>

<script type="text/html" id="banner-bar">
    <button class="pear-btn pear-btn-primary pear-btn-sm" lay-event="edit"><i class="layui-icon layui-icon-edit"></i></button>
    <button class="pear-btn pear-btn-danger pear-btn-sm" lay-event="remove"><i class="layui-icon layui-icon-delete"></i></button>
</script>
<#include "/common/footer.ftl"/>
<script type="text/javascript">
    layui.use(['table', 'form', 'jquery','common'], function() {
        var table = layui.table;
        var form = layui.form;
        var $ = layui.jquery;
        var common = layui.common;

        var MODULE_PATH = "/banner";

        var cols = [];
        var temp = {};
        temp.title = 'id';
        temp.field = 'id';
        temp.align = 'center';
        cols.push(temp);
        var temp = {};
        temp.title = '标题';
        temp.field = 'title';
        temp.align = 'center';
        cols.push(temp);
        var temp = {};
        temp.title = '链接';
        temp.field = 'url';
        temp.align = 'center';
        cols.push(temp);
        var temp = {};
        temp.title = '图片';
        temp.field = 'pic';
        temp.align = 'center';
        cols.push(temp);
        var temp = {};
        temp.title = '描述';
        temp.field = 'desc';
        temp.align = 'center';
        cols.push(temp);
        var temp = {};
        temp.title = '顺序';
        temp.field = 'order';
        temp.align = 'center';
        cols.push(temp);
        var temp = {};
        temp.title = '0-正常，1-删除';
        temp.field = 'deleted';
        temp.align = 'center';
        cols.push(temp);
        var temp = {};
        temp.title = '创建人';
        temp.field = 'createBy';
        temp.align = 'center';
        cols.push(temp);
        var temp = {};
        temp.title = '创建时间';
        temp.field = 'createTime';
        temp.align = 'center';
        cols.push(temp);
        var temp = {};
        temp.title = '更新人';
        temp.field = 'updateBy';
        temp.align = 'center';
        cols.push(temp);
        var temp = {};
        temp.title = '更新时间';
        temp.field = 'updateTime';
        temp.align = 'center';
        cols.push(temp);
        var btns = {};
        btns.title = '操作'
        btns.toolbar = '#banner-bar'
        btns.align = 'center';
        btns.width = 130;
        cols.push(btns);
        var $cols = [];
        $cols.push(cols);
        table.render({
            elem: '#banner-table',
            url: '/banner/list',
            page: true,
            cols: $cols,
            skin: 'line',
            toolbar: '#banner-toolbar',
            defaultToolbar: [],
            page: true,
            response: {
                statusCode: 0
            },
            parseData: function(res){
                return {
                    "code": res.code,
                    "msg": res.message,
                    "count": res.data.total,
                    "data": res.data.list
                };
            }
        });

        table.on('tool(banner-table)', function(obj) {
            if (obj.event === 'remove') {
                window.remove(obj);
            } else if (obj.event === 'edit') {
                window.edit(obj);
            }
        });

        table.on('toolbar(banner-table)', function(obj) {
            if (obj.event === 'add') {
                window.add();
            }
        });

        form.on('submit(banner-query)', function(data) {
            table.reload('banner-table', {
                where: data.field
            })
            return false;
        });

        window.add = function() {
            layer.open({
                type: 2,
                title: 'banner新增',
                shade: 0.1,
                maxmin:true,
                shadeClose: true,
                area: ['100%', '100%'],
                content: MODULE_PATH + '/add.html'
            });
        }

        window.edit = function(obj) {
            layer.open({
                type: 2,
                title: 'banner编辑',
                shade: 0.1,
                maxmin:true,
                shadeClose: true,
                area: ['100%', '100%'],
                content: MODULE_PATH + '/' + obj.data['id'] + '.html'
            });
        }

        window.remove = function(obj) {
            layer.confirm('确定要删除该条数据？', {
                icon: 3,
                title: '提示'
            }, function(index) {
                layer.close(index);
                var loading = layer.load();
                $.ajax({
                    url: MODULE_PATH + "/delete/" + obj.data['id'],
                    dataType: 'json',
                    type: 'post',
                    success: function(result) {
                        layer.close(loading);
                        if (result.code == 0) {
                            layer.msg(result.msg, {
                                icon: 1,
                                time: 1000
                            }, function() {
                                obj.del();
                            });
                        } else {
                            layer.msg(result.message, {
                                icon: 2,
                                time: 1000
                            });
                        }
                    }
                })
            });
        }

        window.refresh = function(param) {
            table.reload('banner-table');
        }
    })
</script>
</body>
</html>
