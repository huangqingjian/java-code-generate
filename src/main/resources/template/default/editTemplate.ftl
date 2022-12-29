<!DOCTYPE html>
<html lang="en">
<head>
    <title>${table.comment}编辑</title>
    <#noparse><#include "/common/head.ftl"/></#noparse>
</head>
<body>
<form class="layui-form" action="" lay-filter="${domain?uncap_first}">
    <div class="mainBox">
        <div class="main-container">
            <input type="hidden" name="${table.primaryColumn.propertyName}" value="<#noparse>${</#noparse>${table.primaryColumn.propertyName}<#noparse>}</#noparse>">
            <#if table.columns?? && table.columns?size gt 0>
            <#list table.columns as column>
            <#if excludeFields?index_of(column) == -1>
            <#if column.name != table.primaryColumn.name>
            <div class="layui-form-item">
                <label class="layui-form-label">${column.comment}</label>
                <div class="layui-input-block">
                    <input type="text" name="${column.propertyName}" lay-verify="title" autocomplete="off" placeholder="请输入${column.comment}" class="layui-input">
                </div>
            </div>
            </#if>
            </#if>
            </#list>
            </#if>
        </div>
    </div>
    <div class="bottom">
        <div class="button-container">
            <button type="submit" class="pear-btn pear-btn-primary pear-btn-sm" lay-submit="" lay-filter="${domain?uncap_first}-save">
                <i class="layui-icon layui-icon-ok"></i>
                提交
            </button>
            <button type="reset" class="pear-btn pear-btn-sm">
                <i class="layui-icon layui-icon-refresh"></i>
                重置
            </button>
        </div>
    </div>
</form>
<#noparse><#include "/common/footer.ftl"/></#noparse>
<script type="text/javascript">
    layui.use(['form', 'jquery'], function() {
        var form = layui.form;
        var $ = layui.jquery;

        // 初始化
        layer.ready(function(){
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/${domain?uncap_first}/get/" + $("input[name=${table.primaryColumn.propertyName}]").val(),
                success: function (data) {
                    if (data.code == "0") {
                        if(data.data) {
                            var result = {};
                            <#if table.columns?? && table.columns?size gt 0>
                            <#list table.columns as column>
                            <#if excludeFields?index_of(column) == -1>
                            result.${column.propertyName} = data.data.${column.propertyName};
                            </#if>
                            </#list>
                            </#if>
                            form.val('${domain?uncap_first}', result);
                        } else {
                            layer.msg("数据不存在～", {
                                time: 2000
                            });
                        }
                    } else {
                        layer.msg(data.message, {
                            time: 2000
                        });
                    }
                }
            });
        });

        form.on('submit(${domain?uncap_first}-save)', function(data) {
            $.ajax({
                url: '/${domain?uncap_first}/update',
                data: JSON.stringify(data.field),
                dataType: 'json',
                contentType: 'application/json',
                type: 'post',
                success: function(result) {
                    if (result.code == 0) {
                        layer.msg('操作成功～', {
                            icon: 1,
                            time: 1000
                        }, function() {
                            parent.layer.close(parent.layer.getFrameIndex(window.name));
                            parent.layui.table.reload("${domain?uncap_first}-table");
                        });
                    } else {
                        layer.msg(result.message, {
                            icon: 2,
                            time: 1000
                        });
                    }
                }
            })
            return false;
        });
    })
</script>
</body>
</html>
