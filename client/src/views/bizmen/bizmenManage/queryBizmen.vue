<template>
  <div>
    <bizmen-list :rule-form="ruleForm" :is-active="true"></bizmen-list>
  </div>
</template>

<script>
import BizmenList from "@/views/bizmen/bizmenManage/bizmenList";
export default {
  name: "queryBizmen",
  components: {BizmenList},
  data() {
    return {
      ruleForm: {
        lowPrice: null,
        highPrice: null,
        name: null
      }
    }
  }
}
</script>

<style scoped>

</style>