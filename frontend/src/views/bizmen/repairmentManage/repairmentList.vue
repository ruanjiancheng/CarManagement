<template>
  <div>
    <el-card>
<!--      <el-button round @click="add()">新建</el-button>-->
      <el-table
          :data="tableData"
          border
          stripe
          style="width: 100%">
        <el-table-column
            fixed
            prop="id"
            label="维修项目 ID"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="name"
            label="维修项目名称"
            width="150">
        </el-table-column>
        <el-table-column
            label="操作"
            width="150">
          <template slot-scope="scope">
            <el-popconfirm
                confirm-button-text='删除'
                cancel-button-text='取消'
                icon="el-icon-info"
                icon-color="red"
                title="删除不可复原"
                @confirm="deleteTeacher(scope.row)"
            >
              <el-button slot="reference" type="text" size="small">删除</el-button>
            </el-popconfirm>
            <el-button @click="editor(scope.row)" type="text" size="small">编辑</el-button>
            <el-button @click="list(scope.row)" type="text" size="small">项目详细</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          background
          layout="prev, pager, next"
          :total="total"
          :page-size="pageSize"
          @current-change="changePage"
      >
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "repairmentList",
  data() {
    return {
      tableData: null,
      pageSize: 10,
      total: null,
      tmpList: null,
      type: sessionStorage.getItem("type"),
    }
  },
  created: function() {
    const that = this
    that.tmpList = null
    that.total = null
    that.tableData = null
    const url = this.$url + '/api/repair/getAllRepairments'
    axios.get(url).then(function (resp) {
      console.log(resp.data.data)
      that.tmpList = resp.data.data
      console.log(that.tmpList)
      that.total = resp.data.data.length
      let start = 0, end = that.pageSize
      let length = that.tmpList.length
      let ans = (end < length) ? end : length
      that.tableData = that.tmpList.slice(start, ans)
    })
  },
  methods: {
    select(row) {
      console.log(row)
    },
    deleteTeacher(row) {
      const that = this
      const url = this.$url + '/api/repair/delete'
      console.log(row)
      axios.get(url + '?id=' + row.id).then(function (resp) {
        console.log(resp)
        if (resp.data.code === 1000) {
          that.$message({
            showClose: true,
            message: '删除成功',
            type: 'success'
          });
          window.location.reload()
        } else {
          that.$message({
            showClose: true,
            message: '删除出错，请查询数据库连接',
            type: 'error'
          });
        }
      }).catch(function (error) {
        that.$message({
          showClose: true,
          message: '删除出错，存在外键依赖',
          type: 'error'
        });
      })
    },
    changePage(page) {
      page = page - 1
      const that = this
      let start = page * that.pageSize, end = that.pageSize * (page + 1)
      let length = that.tmpList.length
      let ans = (end < length) ? end : length
      that.tableData = that.tmpList.slice(start, ans)
    },
    editor(row) {
      this.$router.push({
        path: '/repairmentAddMaterial',
        query: {
          id: row.id,
          name: row.name
        }
      })
    },
    list(row) {
      // TODO 查看维修项目 id 的全部材料名称和数量
      this.$router.push({
        path: '/repairmentDetail',
        query: {
          id: row.id,
          name: row.name
        }
      })
    },
    add() {
      this.$router.push({path: '/addRepairment'})
    }
  }
}
</script>

<style scoped>

</style>