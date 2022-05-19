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
            prop="orderID"
            label="工单 ID"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="repairmentID"
            label="维修项目 ID"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="workTime"
            label="工时"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="statusZh"
            label="状态"
            width="150">
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
      unFinish: [],
      finish: [],
      id: parseInt(sessionStorage.getItem("id")),
      type: sessionStorage.getItem("type"),
    }
  },
  created: function() {
    const that = this
    that.tmpList = null
    that.total = null
    that.tableData = null
    const url = this.$url + '/api/order/queryFinished?id='
    axios.get(url + that.id).then(function (resp) {
      console.log(resp.data.data)
      that.tmpList = resp.data.data
      for (let i = 0; i < that.tmpList.length; i++) {
        that.tmpList[i].statusZh = that.$orderDict[that.tmpList[i].status]
      }
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
    changePage(page) {
      page = page - 1
      const that = this
      let start = page * that.pageSize, end = that.pageSize * (page + 1)
      let length = that.tmpList.length
      let ans = (end < length) ? end : length
      that.tableData = that.tmpList.slice(start, ans)
    },
    editor(row) {
      const that = this
      const url = this.$url + '/api/order/finishOneRepair'
      axios.post(url, {orderID: row.orderID, detailID: row.repairmentID}).then(function (resp) {
        console.log(resp.data)
        if (resp.data.code === 1000) {
          that.$message({
            showClose: true,
            message: '操作成功',
            type: 'success'
          });
        }
        else {
          that.$message.error('操作失败，请检查数据库');
        }
        window.location.reload()
      })
    },
    list(row) {
      // TODO 查看维修项目 id 的全部材料名称和数量
      this.$router.push({
        path: '/taskDetail',
        query: {
          id: row.id,
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