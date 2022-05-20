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
            prop="commissionId"
            label="委托书序号"
            width="150">
        </el-table-column>
<!--        <el-table-column-->
<!--            fixed-->
<!--            prop="orderId"-->
<!--            label="工单序号"-->
<!--            width="150">-->
<!--        </el-table-column>-->
        <el-table-column
            fixed
            prop="statusZh"
            label="当前状态"
            width="150">
        </el-table-column>
        <el-table-column
            label="操作"
            width="200">
          <template slot-scope="scope">
<!--            <el-popconfirm-->
<!--                confirm-button-text='删除'-->
<!--                cancel-button-text='取消'-->
<!--                icon="el-icon-info"-->
<!--                icon-color="red"-->
<!--                title="删除不可复原"-->
<!--                @confirm="deleteTeacher(scope.row)"-->
<!--            >-->
<!--              <el-button slot="reference" type="text" size="small">删除</el-button>-->
<!--            </el-popconfirm>-->
            <el-button @click="change(scope.row)" type="text" size="small" v-if="scope.row.status === 0">派工</el-button>
            <el-button @click="editor(scope.row)" type="text" size="small" v-if="scope.row.status === 0">设置工单</el-button>
            <el-button @click="list(scope.row)" type="text" size="small">工单详细</el-button>
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
    const url = this.$url + '/api/order/getAllOrders'
    axios.get(url).then(function (resp) {
      console.log(resp.data.data)
      that.tmpList = resp.data.data
      console.log(that.tmpList)
      for (let i = 0; i < that.tmpList.length; i++) {
        that.tmpList[i].statusZh = that.$orderDict[that.tmpList[i].status]
      }
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
        path: '/orderAddRepairment',
        query: {
          id: row.id,
        }
      })
    },
    list(row) {
      this.$router.push({
        path: '/orderDetail',
        query: {
          id: row.id,
        }
      })
    },
    add() {
      this.$router.push({path: '/addRepairment'})
    },
    change(row) {
      const that = this
      const url = this.$url + '/api/order/updateOrderStatus'
      axios.post(url, {orderID: row.id, status: 1}).then(function (resp) {
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
      })
    }
  }
}
</script>

<style scoped>

</style>