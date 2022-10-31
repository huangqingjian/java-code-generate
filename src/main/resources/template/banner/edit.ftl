<!DOCTYPE html>
<html lang="en">
<head>
    <title>banner编辑</title>
    <#include "/common/head.ftl"/>
</head>
<body>
<form class="layui-form" action="" lay-filter="banner">
    <div class="mainBox">
        <div class="main-container">
            <input type="hidden" name="id" value="${id}">
            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-block">
                    <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">链接</label>
                <div class="layui-input-block">
                    <input type="text" name="url" lay-verify="title" autocomplete="off" placeholder="请输入链接" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">图片</label>
                <div class="layui-input-block">
                    <input type="text" name="pic" lay-verify="title" autocomplete="off" placeholder="请输入图片" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    <input type="text" name="desc" lay-verify="title" autocomplete="off" placeholder="请输入描述" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">顺序</label>
                <div class="layui-input-block">
                    <input type="text" name="order" lay-verify="title" autocomplete="off" placeholder="请输入顺序" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">0-正常，1-删除</label>
                <div class="layui-input-block">
                    <input type="text" name="deleted" lay-verify="title" autocomplete="off" placeholder="请输入0-正常，1-删除" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">创建人</label>
                <div class="layui-input-block">
                    <input type="text" name="createBy" lay-verify="title" autocomplete="off" placeholder="请输入创建人" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">创建时间</label>
                <div class="layui-input-block">
                    <input type="text" name="createTime" lay-verify="title" autocomplete="off" placeholder="请输入创建时间" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">更新人</label>
                <div class="layui-input-block">
                    <input type="text" name="updateBy" lay-verify="title" autocomplete="off" placeholder="请输入更新人" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">更新时间</label>
                <div class="layui-input-block">
                    <input type="text" name="updateTime" lay-verify="title" autocomplete="off" placeholder="请输入更新时间" class="layui-input">
                </div>
            </div>
        </div>
    </div>
    <div class="bottom">
        <div class="button-container">
            <button type="submit" class="pear-btn pear-btn-primary pear-btn-sm" lay-submit="" lay-filter="banner-save">
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
<#include "/common/footer.ftl"/>
<script type="text/javascript">
    layui.use(['form', 'jquery'], function() {
        var form = layui.form;
        var $ = layui.jquery;

        // 初始化
        layer.ready(function(){
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/banner/get/" + $("input[name=id]").val(),
                success: function (data) {
                    if (data.code == "0") {
                        if(data.data) {
                            var result = {};
                            result.id = data.data.id;
                            result.title = data.data.title;
                            result.url = data.data.url;
                            result.pic = data.data.pic;
                            result.desc = data.data.desc;
                            result.order = data.data.order;
                            result.deleted = data.data.deleted;
                            result.createBy = data.data.createBy;
                            result.createTime = data.data.createTime;
                            result.updateBy = data.data.updateBy;
                            result.updateTime = data.data.updateTime;
                            form.val('banner', result);
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

        form.on('submit(banner-save)', function(data) {
            $.ajax({
                url: '/banner/update',
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
                            parent.layui.table.reload("banner-table");
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
