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
            prop="staffId"
            label="员工 ID"
            width="100">
        </el-table-column>
        <el-table-column
            fixed
            prop="commissionId"
            label="委托书编号"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="numberPlace"
            label="车牌号"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="type"
            label="维修类型"
            width="100">
        </el-table-column>
        <el-table-column
            fixed
            prop="classification"
            label="作业类型"
            width="100">
        </el-table-column>
        <el-table-column
            fixed
            prop="settlementMode"
            label="结算类型"
            width="100">
        </el-table-column>
        <el-table-column
            fixed
            prop="description"
            label="故障描述"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="dueDate"
            label="预计完成时间"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="date"
            label="登记时间"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="statusZh"
            label="当前状态"
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
      tmpList: [],
      unFinish: [],
      finish: [],
      id: parseInt(sessionStorage.getItem("id")),
      type: sessionStorage.getItem("type"),
    }
  },
  created: function() {
    const that = this
    that.tmpList = []
    that.total = null
    that.tableData = null
    const url = this.$url + '/api/comm/getAllCommissions'
    axios.get(url).then(function (resp) {
      console.log(resp.data.data)
      let l = resp.data.data
      for (let i = 0; i < l.length; i++) {
        console.log(l[i])
        if (l[i].clientId === sessionStorage.getItem('clientId')) {
          l[i].statusZh = that.$dict[l[i].status]
          that.tmpList.push(l[i])
        }
      }
      console.log(that.tmpList)

      that.total = that.tmpList.length
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