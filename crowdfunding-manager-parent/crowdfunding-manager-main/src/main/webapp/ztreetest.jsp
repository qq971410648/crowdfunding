<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<HTML>
<HEAD>
    <TITLE> 树型结构 </TITLE>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ztree.core-3.5.js"></script>

    <%--********************************************--%>
    <script type="text/javascript">
        var setting = { };

        /**
         * 最外面是一个 [ ]
         * 里面是一个{ }
         */
        var zNodes = [
            {
                //默认展开的节点，请设置 treeNode.open 属性
                name: "父节点1 - 展开",
                open: true,
                children: [
                    {
                        name: "父节点11 - 折叠",
                        open:true,
                        children: [
                            {name: "叶子节点111"},
                            {name: "叶子节点112"},
                            {name: "叶子节点113"},
                            {name: "叶子节点114"}
                        ]
                    },
                    {
                        name: "父节点12 - 折叠",
                        children: [
                            {name: "叶子节点121"},
                            {name: "叶子节点122"},
                            {name: "叶子节点123"},
                            {name: "叶子节点124"}
                        ]
                    },
                    {name: "父节点13 - 没有子节点", isParent: true}
                ]
            },
            {
                name: "父节点2 - 折叠",
                children: [
                    {
                        name: "父节点21 - 展开", open: true,
                        children: [
                            {name: "叶子节点211"},
                            {name: "叶子节点212"},
                            {name: "叶子节点213"},
                            {name: "叶子节点214"}
                        ]
                    },
                    {
                        name: "父节点22 - 折叠",
                        children: [
                            {name: "叶子节点221"},
                            {name: "叶子节点222"},
                            {name: "叶子节点223"},
                            {name: "叶子节点224"}
                        ]
                    },
                    {
                        name: "父节点23 - 折叠",
                        children: [
                            {name: "叶子节点231"},
                            {name: "叶子节点232"},
                            {name: "叶子节点233"},
                            {name: "叶子节点234"}
                        ]
                    }
                ]
            },

            //无子节点的父节点，请设置 treeNode.isParent 属性
            {name: "父节点3 - 没有子节点", isParent: true}
        ];

        $(document).ready(function () {
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });
    </script>
    <%--********************************************--%>

</HEAD>

<BODY>
<h1>最简单的树 -- 标准 JSON 数据</h1>
<div class="content_wrap">

    <%--树形结构--%>
    <div class="zTreeDemoBackground left">
        <ul id="treeDemo" class="ztree">

            <%--内容区域____________这块内容将由tree显示--%>

        </ul>
    </div>



    <div class="right">
        <ul class="info">
            <li class="title"><h2>1、setting 配置信息说明</h2>
                <ul class="list">
                    <li class="highlight_red">普通使用，无必须设置的参数</li>
                    <li>与显示相关的内容请参考 API 中 setting.view 内的配置信息</li>
                    <li>name、children、title 等属性定义更改请参考 API 中 setting.data.key 内的配置信息</li>
                    <li>api在线地址：<a href="http://www.treejs.cn/v3/api.php" target="_blank">在线api</a></li>
                </ul>
            </li>
            <li class="title"><h2>2、treeNode 节点数据说明</h2>
                <ul class="list">
                    <li class="highlight_red">标准的 JSON 数据需要嵌套表示节点的父子包含关系
                        <div>
                            <pre xmlns="">
                                <code>
                                    例如：
                                        var nodes = [
                                            {name: "父节点1", children: [
                                                {name: "子节点1"},
                                                {name: "子节点2"}
                                            ]}
                                        ];
                                </code>
                            </pre>
                        </div>
                    </li>
                    <li>默认展开的节点，请设置 treeNode.open 属性</li>
                    <li>无子节点的父节点，请设置 treeNode.isParent 属性</li>
                    <li>其他属性说明请参考 API 中 "treeNode 节点数据详解"</li>
                </ul>
            </li>
        </ul>
    </div>
</div>
</BODY>
</HTML>