<!DOCTYPE html>
<html>
<head>
    <title>${table.comment}管理</title>
    <#noparse><#include "/common/head.ftl"/></#noparse>
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
                    <button class="pear-btn pear-btn-md pear-btn-primary" lay-submit lay-filter="${domain?uncap_first}-query">
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
        <table id="${domain?uncap_first}-table" lay-filter="${domain?uncap_first}-table"></table>
    </div>
</div>

<script type="text/html" id="${domain?uncap_first}-toolbar">
    <button class="pear-btn pear-btn-primary pear-btn-md" lay-event="add">
        <i class="layui-icon layui-icon-add-1"></i>
        新增
    </button>
</script>

<script type="text/html" id="${domain?uncap_first}-bar">
    <button class="pear-btn pear-btn-primary pear-btn-sm" lay-event="edit"><i class="layui-icon layui-icon-edit"></i></button>
    <button class="pear-btn pear-btn-danger pear-btn-sm" lay-event="remove"><i class="layui-icon layui-icon-delete"></i></button>
</script>
<#noparse><#include "/common/footer.ftl"/></#noparse>
<script type="text/javascript">
    layui.use(['table', 'form', 'jquery','common'], function() {
        var table = layui.table;
        var form = layui.form;
        var $ = layui.jquery;
        var common = layui.common;

        var MODULE_PATH = "/${domain?uncap_first}";

        var cols = [];
        <#if table.columns?? && table.columns?size gt 0>
        <#list table.columns as column>
        var temp = {};
        temp.title = '${column.comment}';
        temp.field = '${column.propertyName}';
        temp.align = 'center';
        cols.push(temp);
        </#list>
        </#if>
        var btns = {};
        btns.title = '操作'
        btns.toolbar = '#${domain?uncap_first}-bar'
        btns.align = 'center';
        btns.width = 130;
        cols.push(btns);
        var $cols = [];
        $cols.push(cols);
        table.render({
            elem: '#${domain?uncap_first}-table',
            url: '/${domain?uncap_first}/list',
            page: true,
            cols: $cols,
            skin: 'line',
            toolbar: '#${domain?uncap_first}-toolbar',
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

        table.on('tool(${domain?uncap_first}-table)', function(obj) {
            if (obj.event === 'remove') {
                window.remove(obj);
            } else if (obj.event === 'edit') {
                window.edit(obj);
            }
        });

        table.on('toolbar(${domain?uncap_first}-table)', function(obj) {
            if (obj.event === 'add') {
                window.add();
            }
        });

        form.on('submit(${domain?uncap_first}-query)', function(data) {
            table.reload('${domain?uncap_first}-table', {
                where: data.field
            })
            return false;
        });

        window.add = function() {
            layer.open({
                type: 2,
                title: '${table.comment}新增',
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
                title: '${table.comment}编辑',
                shade: 0.1,
                maxmin:true,
                shadeClose: true,
                area: ['100%', '100%'],
                content: MODULE_PATH + '/' + obj.data['${table.primaryColumn.propertyName}'] + '.html'
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
                    url: MODULE_PATH + "/delete/" + obj.data['${table.primaryColumn.propertyName}'],
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
            table.reload('${domain?uncap_first}-table');
        }
    })
</script>
</body>
</html>
